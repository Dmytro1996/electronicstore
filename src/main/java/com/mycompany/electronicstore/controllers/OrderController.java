/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.Accesorie;
import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.model.Order;
import com.mycompany.electronicstore.model.Television;
import com.mycompany.electronicstore.service.OrderService;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.UserProfile;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
    
    private OrderService orderService;
    Logger logger=LoggerFactory.getLogger(OrderController.class);
    private List<Commodity> basket;
    @Autowired
    private Client client;
    @Autowired
    private JavaMailSender emailSender;
    
    @Autowired
    public OrderController(OrderService orderService, List<Commodity> basket){
        this.orderService=orderService;
        this.basket=basket;
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public String getAll(Model model){
        addOrdersToModel(model, orderService.getAll());
        logger.info("Size:"+orderService.getAll().size());
        return "orders";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/read/{id}")
    public String read(@PathVariable("id")long id, Model model){
        Order order=orderService.readById(id);
        UserProfile user=client.getUser(order.getUserId()).getProfile();
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        return "/order-details";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        orderService.delete(id);
        return "redirect:/orders/all";
    }    
    
    @PostMapping("/create")
    public String create(@AuthenticationPrincipal OidcUser user){
        Order order=new Order();        
        order.setTvs((basket.stream().filter(c->c instanceof Television)
                .map(c->(Television)c)).collect(Collectors.toList()));
        order.setMobiles((basket.stream().filter(c->c instanceof MobileDevice)
                .map(c->(MobileDevice)c)).collect(Collectors.toList()));
        order.setLaptops((basket.stream().filter(c->c instanceof Laptop)
                .map(c->(Laptop)c)).collect(Collectors.toList()));
        order.setAcc((basket.stream().filter(c->c instanceof Accesorie)
                .map(c->(Accesorie)c)).collect(Collectors.toList()));
        order.setUserId(user.getName());
        orderService.create(order);
        sendEmail(user.getGivenName(), user.getEmail());        
        basket.clear();        
        return "redirect:/index";
    }    
    
    @PostMapping("/remove/{index}")
    public String remove(@RequestHeader("Referer") String referer,@PathVariable("index")int index){
        basket.remove(index);
        logger.info(referer);
        if(referer.contains("filter")){
            referer=referer.replace("filter", "all");
        }        
        return "redirect:"+referer+"?shouldBasketBeOpened=true";
    }
    
    @PostMapping("/filter")
    public String filter(@RequestParam("isExecuted") String isExecuted,Model model){
        logger.info("isExecuted: "+isExecuted);
        logger.info("isExecuted equals to \"all\": "+!isExecuted.equals("all"));
        if(!isExecuted.equals("all")){
            logger.info("inside if block in OrderController.filter()");
            addOrdersToModel(model, orderService.getAll().stream().filter(o->
                    o.isExecuted()==Boolean.valueOf(isExecuted)).collect(Collectors.toList()));
            return "orders";
        }
        return "redirect:/orders/all";
    }
    
    @PostMapping("/execute/{id}")
    public String execute(@PathVariable("id")long id){
        Order order=orderService.readById(id);
        order.setExecuted(true);
        orderService.create(order);
        return "redirect:/orders/read/"+id;
    }
    
    private void addOrdersToModel(Model model, List<Order> orders){
        Map<Order, String> orderMap=new HashMap<>();
        orders.stream().forEach(order->{
            UserProfile userProfile=client.getUser(order.getUserId()).getProfile();
            orderMap.put(order, userProfile.getFirstName()+" "+userProfile.getLastName());});
        model.addAttribute("orderMap", orderMap.entrySet());
    }
    
    private void sendEmail(String username, String receiver){
        String emailText="Hi "+username+"! Your order have been received and"+
                " will be processed. You will be contacted by manager soon enough.";
        SimpleMailMessage message=new SimpleMailMessage();
        message.setTo(receiver);
        message.setSubject("New order");
        message.setText(emailText);
        emailSender.send(message);
        logger.info("Email sent");
    }
}
