/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.Role;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.service.UserService;
import com.mycompany.electronicstore.service.impl.UserServiceImpl;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    
    @Autowired
    public UserController(UserServiceImpl userService){
        this.userService=userService;
    }
    
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("user",new User());
        return "registration";
    }
    
    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("user")User user,Model model, BindingResult result){
        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "registration";
        }
        user.setRole(Role.USER);
        userService.create(user);
        return "redirect:/index";
    }
    
}
