/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.impl.LaptopServiceImpl;
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
@RequestMapping("/laptop")
public class LaptopController {
    
    @Autowired
    private LaptopServiceImpl laptopService;
    
    @GetMapping("/all")
    private String getAll(Model model){
        model.addAttribute("brands",laptopService.getBrands());
        model.addAttribute("screenSizes",laptopService.getScreenSizes());
        model.addAttribute("resolutions",laptopService.getResolutions());
        model.addAttribute("internalMemories",laptopService.getIntMems());
        model.addAttribute("operMems", laptopService.getOperMems());
        model.addAttribute("laptops",laptopService.getAllAsHTML());
        return "laptops";
    }
    
    @PostMapping("/filter")
    private String getFiltered(Model model,HttpServletRequest request){
        model.addAttribute("brands",laptopService.getBrands());
        model.addAttribute("screenSizes",laptopService.getScreenSizes());
        model.addAttribute("resolutions",laptopService.getResolutions());
        model.addAttribute("internalMemories",laptopService.getIntMems());
        model.addAttribute("operMems", laptopService.getOperMems());
        model.addAttribute("laptops", laptopService.getByCriterias(Double.valueOf(request.getParameter("minPrice")),
                Double.valueOf(request.getParameter("maxPrice")), request.getParameter("screenSize"), 
                request.getParameter("brand"), request.getParameter("resolution"),
                request.getParameterValues("operMem"),request.getParameter("intMem")));
        return "laptops";
    }
}
