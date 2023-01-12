/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class MobileControllerTests {
     
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/mobile/all")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("mobiles"));
    }
    
    @Test
    public void filterTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/mobile/filter").param("minPrice", "1000")
                .param("maxPrice", "100000").param("brand", "Apple").param("screenSize", "5")
                .param("intMem", "All").param("extMem", "All")
                .param("camera", "All").param("simCount", "Any")
                .param("gps", new String[]{"true"}))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("mobiles"));
    }
    
    @Test
    public void buyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/mobile/buy/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void buyWithInvalidIdTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/mobile/buy/-1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
