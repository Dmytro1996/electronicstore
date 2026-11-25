/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.ai;

import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.service.AccesorieService;
import com.mycompany.electronicstore.service.LaptopService;
import com.mycompany.electronicstore.service.MobileDeviceService;
import com.mycompany.electronicstore.service.TelevisionService;
import java.util.List;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author User
 */
@Component
public class CartFillmentTool {

    private List<Commodity> basket;

    @Autowired
    public CartFillmentTool(List<Commodity> basket) {
        this.basket = basket;
    }
    
    @Tool
    public void addToCart(
            @ToolParam(description = "An item to add to shopping cart") 
                    Laptop commodity) {
        basket.add(commodity);
    }
}
