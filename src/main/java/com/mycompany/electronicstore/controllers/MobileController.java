/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.service.MobileDeviceService;
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

/**
 *
 * @author dmytr
 */
@Controller
@RequestMapping("/mobile")
public class MobileController {
    
    Logger logger=LoggerFactory.getLogger(MobileController.class);
    @Autowired
    private MobileDeviceService mobileService;
    
    /*@Autowired
    public MobileController(MobileDeviceService mobileService){
        this.mobileService=mobileService;
    }*/
    
    @GetMapping("/all")
    public String getAll(Model model){        
        model.addAttribute("brands",mobileService.getBrands());
        model.addAttribute("screenSizes",mobileService.getScreenSizes());
        model.addAttribute("resolutions",mobileService.getResolutions());
        model.addAttribute("internalMemories",mobileService.getIntMems());
        model.addAttribute("operMems", mobileService.getOperMems());
        model.addAttribute("externalMemories",mobileService.getExtMems());
        model.addAttribute("cameras",mobileService.getCameras());
        model.addAttribute("mobiles", mobileService.getAllAsHTML());
        return "mobile";
    }
    
    @PostMapping("/filter")
    public String getFiltered(Model model,HttpServletRequest request){
        logger.info(request.getParameter("minPrice"));
        logger.info(request.getParameter("screenSize"));
        logger.info(request.getParameter("brand"));
        logger.info(Arrays.toString(request.getParameterValues("operMem")));
        logger.info(request.getParameter("intMem"));
        logger.info("Sim count:"+Arrays.toString(request.getParameterValues("simCount")));
        logger.info(Arrays.toString(request.getParameterValues("gps")));
        model.addAttribute("brands",mobileService.getBrands());
        model.addAttribute("screenSizes",mobileService.getScreenSizes());
        model.addAttribute("resolutions",mobileService.getResolutions());
        model.addAttribute("internalMemories",mobileService.getIntMems());
        model.addAttribute("operMems", mobileService.getOperMems());
        model.addAttribute("externalMemories",mobileService.getExtMems());
        model.addAttribute("cameras",mobileService.getCameras());
        model.addAttribute("mobiles", mobileService.getByCriterias(Double.valueOf(request.getParameter("minPrice")),
                Double.valueOf(request.getParameter("maxPrice")), request.getParameter("screenSize"), 
                request.getParameter("brand"),/* request.getParameter("resolution"),*/
                request.getParameterValues("operMem"),request.getParameter("intMem"), 
                request.getParameter("extMem"), request.getParameter("camera"),
                request.getParameterValues("simCount"),request.getParameterValues("gps")));
        return "mobile";
    }
}
