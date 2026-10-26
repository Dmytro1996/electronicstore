/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.ai;

import com.mycompany.electronicstore.service.AccesorieService;
import com.mycompany.electronicstore.service.LaptopService;
import com.mycompany.electronicstore.service.MobileDeviceService;
import com.mycompany.electronicstore.service.TelevisionService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author User
 */
@Configuration(proxyBeanMethods = false)
public class AIChatConfig {
    
    @Bean
    @Qualifier("availableLaptops")
    public ToolCallback getAllLaptops(LaptopService laptopService) {
        return FunctionToolCallback.builder("availableLaptops", laptopService::getAll)
                .description("Retrieves all the laptops available for sale").build();
    }
    
    @Bean    
    @Qualifier("availableMobileDevices")
    public ToolCallback getAllMobileDevices(MobileDeviceService mobileDeviceService) {
        return FunctionToolCallback.builder("availableMobileDevices", 
                mobileDeviceService::getAll).description(
                        "Retrieves all the mobile devices available for sale")
                .build();
    }
    
    @Bean
    @Qualifier("availableTelevisions")
    public ToolCallback getAllTvs(TelevisionService tvService) {
        return FunctionToolCallback.builder("availableTelevisions", tvService::getAll)
                .description("Retrieves all the televisions available for sale")
                .build();
    }
    
    @Bean
    @Qualifier("availableAccesories")
    public ToolCallback getAllAccesories(AccesorieService accesorieService) {
        return FunctionToolCallback.builder("availableAccesories", accesorieService::getAll)
                .description("Retrieves all the accesories available for sale")
                .build();
    }
}
