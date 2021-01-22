/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controllers;

import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.service.impl.LaptopServiceImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/laptop")
public class LaptopController {
    
    @Autowired
    private LaptopServiceImpl laptopService;
    @Autowired
    private List<Commodity> basket;
    
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
    private String getFiltered(Model model, @RequestParam("minPrice")String minPrice,
            @RequestParam("maxPrice")String maxPrice, @RequestParam("brand")String brand,
            @RequestParam("screenSize")String screenSize,@RequestParam("resolution")String resolution, 
            @RequestParam("intMem")String intMem,HttpServletRequest request){
        model.addAttribute("brands",laptopService.getBrands());
        model.addAttribute("screenSizes",laptopService.getScreenSizes());
        model.addAttribute("resolutions",laptopService.getResolutions());
        model.addAttribute("internalMemories",laptopService.getIntMems());
        model.addAttribute("operMems", laptopService.getOperMems());
        model.addAttribute("laptops", laptopService.getByCriterias(Double.valueOf(minPrice),
                Double.valueOf(maxPrice), screenSize, brand, resolution,
                request.getParameterValues("operMem"),intMem));
        return "laptops";
    }
    
    @PostMapping("/buy/{id}")
    public String buy(@PathVariable("id") long id){
        basket.add(laptopService.getAll().stream().filter(c->c.getId()==id).findFirst().get());        
        return "redirect:\\laptop\\all";
    }
}
