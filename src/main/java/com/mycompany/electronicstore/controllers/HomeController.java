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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
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
    public String home(Model model, @AuthenticationPrincipal OidcUser user){
        List<? extends Commodity> comms=orderService.getAll().stream()
                .map(c->c.getCommodities()).flatMap(Collection::stream).distinct()
                .sorted((c1,c2)->{return c2.getOrders().size()-c1.getOrders().size();})
                .limit(4).collect(Collectors.toList()); 
        logger.info("User info:\n");
        logger.info("User is null:"+(user==null));
        logger.info("User: "+(user==null?"null":user.getGivenName()));
        logger.info("User id: "+(user==null?"null":user.getName()));
        logger.info("Authorities:"+(user==null?"null":user.getAuthorities()));
        logger.info("Comms:"+comms.toString());
        model.addAttribute("popularCommodities", comms);        
        model.addAttribute("basket",basket.stream().collect(Collectors.toList()));
        return "index";        
    }
}
