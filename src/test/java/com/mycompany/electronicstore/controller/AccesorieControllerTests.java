/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controller;

import com.mycompany.electronicstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class AccesorieControllerTests {
     
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
    private UserServiceImpl userService;    
    
    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/acc/all")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("acc"));
    }
    
    @Test
    public void filterTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/acc/filter").param("minPrice", "1000")
                .param("maxPrice", "10000").param("brand", "All brands")
                .param("type", new String[]{"Keyboard","Computer mouse"}))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("acc"));
    }
    
    @Test
    public void buyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/acc/buy/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void buyWithInvalidIdTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/acc/buy/-1"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
}
