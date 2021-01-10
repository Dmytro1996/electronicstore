/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.TelevisionService;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/tv")
public class TvController {
    
    Logger logger=LoggerFactory.getLogger(TvController.class);
    @Autowired
    TelevisionService tvService;
    
    @GetMapping("/all")
    public String getAll(Model model){
        logger.info(tvService.getScreenSizes().toString());
        model.addAttribute("brands",tvService.getBrands());
        model.addAttribute("screenSizes",tvService.getScreenSizes());
        model.addAttribute("resolutions",tvService.getResolutions());
        model.addAttribute("tvs", tvService.getAllAsHTML());
        return "tv";
    }
    
    @PostMapping("/filter")
    public String filter(Model model, @RequestParam("minPrice")String minPrice, 
            @RequestParam("maxPrice")String maxPrice, @RequestParam("brand")String brand, 
            @RequestParam("screenSizeRange")String screenSizeRange,@RequestParam("resolution")String resolution, 
            HttpServletRequest request){
        model.addAttribute("brands",tvService.getBrands());
        model.addAttribute("screenSizes",tvService.getScreenSizes());
        model.addAttribute("resolutions",tvService.getResolutions());
        model.addAttribute("tvs", tvService.getByCriterias(Double.valueOf(minPrice),
                Double.valueOf(maxPrice), screenSizeRange, brand, resolution, 
                request.getParameterValues("smartTv"), request.getParameterValues("threeD")));
        return "tv";
    }
    
}
