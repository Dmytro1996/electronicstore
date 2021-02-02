/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.details.UserDetailsImpl;
import com.mycompany.electronicstore.model.Role;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.service.UserService;
import com.mycompany.electronicstore.service.impl.UserServiceImpl;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/users")
public class UserController {
    
    private UserServiceImpl userService;
    Logger logger=LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }
    
    @GetMapping("/create")
    public String create(Model model){
        logger.info("Inside create(Get)");
        User user=new User();
        user.setRole(Role.USER);
        model.addAttribute("user",user);        
        return "registration";
    }
    
    @PostMapping("/create")
    public String create(Model model,@Validated @ModelAttribute("user")User user, BindingResult result){        
        if(result.hasErrors()){
            logger.info("has errors"+
            result.getAllErrors().toString());
            model.addAttribute("user", user);
            return "registration";
        } 
        logger.info("Does not have errors");
        //userService.create(user);
        UserDetailsImpl userDetails=new UserDetailsImpl(userService.create(user));
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),userDetails.getAuthorities()));
        return "redirect:/index";
    }
    
}
