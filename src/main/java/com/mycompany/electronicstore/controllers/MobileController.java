/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.service.MobileDeviceService;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    @Autowired
    private List<Commodity> basket;
    
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
    public String getFiltered(Model model, @RequestParam("minPrice")String minPrice,
            @RequestParam("maxPrice")String maxPrice, @RequestParam("brand")String brand,
            @RequestParam("screenSize")String screenSize, 
            @RequestParam("intMem")String intMem,@RequestParam("extMem")String extMem,
            @RequestParam("camera")String camera,HttpServletRequest request){
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
        model.addAttribute("mobiles", mobileService.getByCriterias(Double.valueOf(minPrice),
                Double.valueOf(maxPrice), screenSize, brand,
                request.getParameterValues("operMem"),intMem, extMem, camera,
                request.getParameterValues("simCount"),request.getParameterValues("gps")));
        return "mobile";
    }
    
    @PostMapping("/buy/{id}")
    public String buy(@PathVariable("id") long id){
        basket.add(mobileService.getAll().stream().filter(c->c.getId()==id).findFirst().get());        
        return "redirect:\\mobile\\all";
    }
}
