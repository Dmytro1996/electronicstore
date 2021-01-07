/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.TelevisionService;
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
@RequestMapping("/tv")
public class TvController {
    
    @Autowired
    TelevisionService tvService;
    
    @GetMapping("/all")
    public String all(Model model){
        model.addAttribute("tvs", tvService.getAllAsHTML());
        return "tv";
    }
    
    @PostMapping("/filter")
    public String filter(Model model,HttpServletRequest request){
        model.addAttribute("tvs", tvService.getByCriterias(Double.valueOf(request.getParameter("minPrice")),
                Double.valueOf(request.getParameter("maxPrice")), request.getParameter("screenSizeRange"), 
                request.getParameter("brand"), request.getParameter("resolution"), 
                request.getParameterValues("smartTv"), request.getParameterValues("threeD")));
        return "tv";
    }
    
}
