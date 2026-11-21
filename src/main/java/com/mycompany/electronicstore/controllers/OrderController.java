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
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.service.OrderService;
import com.mycompany.electronicstore.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    
    private final OrderService orderService;
    
    private final Logger logger=LoggerFactory.getLogger(OrderController.class);
    
    private final List<Commodity> basket;
    
    private final JavaMailSender emailSender;
    
    private final UserService userService;
    
    @Autowired
    public OrderController(OrderService orderService, List<Commodity> basket, 
            UserService userService, JavaMailSender emailSender){
        this.orderService=orderService;
        this.basket=basket;
        this.userService = userService;
        this.emailSender = emailSender;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        logger.info("Size:"+orderService.getAll().size());
        return "orders";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/read/{id}")
    public String read(@PathVariable("id")long id, Model model){
        Order order=orderService.readById(id);
        model.addAttribute("order", order);
        return "order-details";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        orderService.delete(id);
        return "redirect:/orders/all";
    }    
    
    @PostMapping("/create")
    public String create(@AuthenticationPrincipal OidcUser authUser){        
        Order order=new Order();        
        order.setTvs((basket.stream().filter(c->c instanceof Television)
                .map(c->(Television)c)).collect(Collectors.toList()));
        order.setMobiles((basket.stream().filter(c->c instanceof MobileDevice)
                .map(c->(MobileDevice)c)).collect(Collectors.toList()));
        order.setLaptops((basket.stream().filter(c->c instanceof Laptop)
                .map(c->(Laptop)c)).collect(Collectors.toList()));
        order.setAcc((basket.stream().filter(c->c instanceof Accesorie)
                .map(c->(Accesorie)c)).collect(Collectors.toList()));
        Optional<User> optionalUser = userService.getAll().stream().filter(
                savedUser -> savedUser.getEmail().equals(authUser.getEmail()))
                .findFirst();
        if(optionalUser.isPresent()) {
            order.setUser(optionalUser.get());
        } else {
            User user = new User();
            user.setName(authUser.getAttribute("name"));
            user.setEmail(authUser.getEmail());
            order.setUser(userService.create(user));
        }
        orderService.create(order);
        sendEmail(authUser.getAttribute("name"), authUser.getEmail());        
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
            model.addAttribute("orders", orderService.getAll().stream().filter(o->
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
