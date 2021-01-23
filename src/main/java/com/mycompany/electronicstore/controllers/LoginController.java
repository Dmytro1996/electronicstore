/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
public class LoginController {
    
    Logger logger=LoggerFactory.getLogger(LoginController.class);
    
    @RequestMapping("/login")
    public String login(){
        logger.info("Inside login");
        return "redirect:/index";
    }
    
    @RequestMapping("/logout")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "redirect:/index";
    }
}
