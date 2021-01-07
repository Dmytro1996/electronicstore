/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.AccesorieService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/acc")
public class AccesorieController {
    
    @Autowired
    private AccesorieService accService;
    
    @GetMapping("/all")
    public String getAll(Model model){
       model.addAttribute("acc", accService.getAllAsHTML());
       return "accesories";
    }
    
    @PostMapping("/filter")
    public String getFiltered(Model model,HttpServletRequest request){
        model.addAttribute("acc", accService.getByCriterias(Double.valueOf(request.getParameter("minPrice")),
                Double.valueOf(request.getParameter("maxPrice")),
                request.getParameter("brand"), request.getParameterValues("names")));
        return "accesories";
    }
}
