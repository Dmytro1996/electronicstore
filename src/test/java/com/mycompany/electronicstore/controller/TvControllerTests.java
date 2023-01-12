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
public class TvControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/tv/all")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("tvs"));
    }
    
    @Test
    public void filterTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tv/filter").param("minPrice", "10000")
                .param("maxPrice", "50000").param("brand", "Samsung").param("screenSizeRange", "40-50")
                .param("resolution", "All resolutions").param("smartTv", new String[]{"true"})
                .param("threeD", new String[]{"true"}))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("tvs"));
    }
    
    @Test
    public void buyTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tv/buy/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void buyWithInvalidIdTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/tv/buy/-1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
