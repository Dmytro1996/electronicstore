/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.service.OrderService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
public class HomeController {
    
    @Autowired
    private OrderService orderService;
    @Autowired
    private List<Commodity> basket;
    Logger logger=LoggerFactory.getLogger(HomeController.class);
   
    @RequestMapping({"/","/index"})
    public String home(Model model){
        List<? extends Commodity> comms=orderService.getAll().stream()
                .map(c->c.getCommodities()).flatMap(Collection::stream).distinct()
                .sorted((c1,c2)->{return c2.getOrders().size()-c1.getOrders().size();})
                .limit(4).collect(Collectors.toList());        
        logger.info("Comms:"+comms.toString());
        model.addAttribute("popularCommodities", comms);        
        model.addAttribute("basket",basket.stream().collect(Collectors.toList()));
        return "index";        
    }
}
