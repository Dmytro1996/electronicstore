/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.details.UserDetailsImpl;
import com.mycompany.electronicstore.model.Accesorie;
import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.model.Order;
import com.mycompany.electronicstore.model.Television;
import com.mycompany.electronicstore.service.OrderService;
import com.mycompany.electronicstore.service.UserService;
import com.mycompany.electronicstore.service.impl.UserServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

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
    private UserServiceImpl userService;
    
    @Autowired
    public OrderController(OrderService orderService, List<Commodity> basket){
        this.orderService=orderService;
        this.basket=basket;
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        logger.info("Size:"+orderService.getAll().size());
        return "orders";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/read/{id}")
    public String read(@PathVariable("id")long id, Model model){
        model.addAttribute("order", orderService.readById(id));
        return "/order-details";
    }
    
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")long id){
        orderService.delete(id);
        return "redirect:/orders/all";
    }    
    
    @PostMapping("/create")
    public String create(){
        Order order=new Order();        
        order.setTvs((basket.stream().filter(c->c instanceof Television)
                .map(c->(Television)c)).collect(Collectors.toList()));
        order.setMobiles((basket.stream().filter(c->c instanceof MobileDevice)
                .map(c->(MobileDevice)c)).collect(Collectors.toList()));
        order.setLaptops((basket.stream().filter(c->c instanceof Laptop)
                .map(c->(Laptop)c)).collect(Collectors.toList()));
        order.setAcc((basket.stream().filter(c->c instanceof Accesorie)
                .map(c->(Accesorie)c)).collect(Collectors.toList()));
        UserDetailsImpl user=(UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setUser(userService.readById(user.getId()));
        orderService.create(order);
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
}
