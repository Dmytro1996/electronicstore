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
public class UserControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void createGetTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/users/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }
    
    @Test
    public void createPostTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create").param("firstName", "John")
                .param("lastName", "Johnson").param("email", "johnjohnson@mail.com")
                .param("password", "q1w2e3r4").param("role", "USER"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void createPostInvalidUserTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/users/create").param("firstName", "john")
                .param("lastName", "johnson").param("email", "johnjohnson@mail")
                .param("password", ""))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }
}
