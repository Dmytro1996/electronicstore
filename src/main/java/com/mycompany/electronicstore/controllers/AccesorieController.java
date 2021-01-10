/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.AccesorieService;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/acc")
public class AccesorieController {
    
    Logger logger=LoggerFactory.getLogger(AccesorieController.class);
    @Autowired
    private AccesorieService accService;
    
    @GetMapping("/all")
    public String getAll(Model model){
       logger.info(accService.getTypes().toString());
       model.addAttribute("brands",accService.getBrands());
       model.addAttribute("types",accService.getTypes());
       model.addAttribute("acc", accService.getAllAsHTML());
       return "accesories";
    }
    
    @PostMapping("/filter")
    public String getFiltered(Model model, @RequestParam("minPrice")String minPrice,
            @RequestParam("maxPrice")String maxPrice, @RequestParam("brand")String brand,
            HttpServletRequest request){        
        model.addAttribute("acc", accService.getByCriterias(Double.valueOf(minPrice),
                Double.valueOf(maxPrice),brand, request.getParameterValues("type")));
        return "accesories";
    }
}
