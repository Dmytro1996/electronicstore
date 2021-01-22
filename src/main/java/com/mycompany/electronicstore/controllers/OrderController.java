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
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public OrderController(OrderService orderService, List<Commodity> basket){
        this.orderService=orderService;
        this.basket=basket;
    }
    
    @GetMapping("/all")
    public String getAll(Model model){
        model.addAttribute("orders", orderService.getAll());
        logger.info("Size:"+orderService.getAll().size());
        return "orders";
    }
    
    @GetMapping("/read/{id}")
    public String read(@PathVariable("id")long id, Model model){
        model.addAttribute("order", orderService.readById(id));
        return "/order-details";
    }
    
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
        order.setUser(orderService.readById(1).getUser());
        orderService.create(order);
        basket.clear();        
        return "redirect:/index";
    }
}
