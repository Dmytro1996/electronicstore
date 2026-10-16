/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.ai;

import com.mycompany.electronicstore.service.LaptopService;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.function.FunctionToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author User
 */
@Configuration(proxyBeanMethods = false)
public class AIChatConfig {
    
    @Bean
    public ToolCallback getAllLaptops(LaptopService laptopService) {
        return FunctionToolCallback.builder("availableLaptops", laptopService::getAll)
                .description("Retrieves all the laptops available for sale").build();
    }
}
