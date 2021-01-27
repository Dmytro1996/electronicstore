/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.controller;

import com.mycompany.electronicstore.details.UserDetailsImpl;
import com.mycompany.electronicstore.model.Commodity;
import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.model.Order;
import com.mycompany.electronicstore.service.OrderService;
import com.mycompany.electronicstore.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class OrderControllerTests {
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private UserService userService;
    @Autowired
    private List<Commodity> basket;
    
    @BeforeEach
    public void setUp(){
        mockMvc=MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity()).build();
        UserDetails userDetails=new UserDetailsImpl(userService.readById(1));
        Authentication auth=new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),userDetails.getAuthorities());
        SecurityContext securityContext=Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(securityContext);        
    }
    
    @Test
    public void getAllTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("orders"));
        List<Order> actualOrders=(List<Order>)mockMvc.perform(MockMvcRequestBuilders
                .get("/orders/all")).andReturn().getModelAndView().getModel().get("orders");
        assertIterableEquals(orderService.getAll().stream().map(o->o.getId())
                .collect(Collectors.toList()),actualOrders.stream().map(o->o.getId())
                .collect(Collectors.toList()));
    }
    
    @Test
    public void readTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/read/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("order"));
    }
    
    @Test
    public void readUnexistingOrderTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/read/-1"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    
    @Test
    public void deleteTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/delete/1"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    
    @Test
    public void deleteUnexistingOrderTest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/orders/delete/-1"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }
    
    @Test
    public void createTest() throws Exception{
        basket.addAll(orderService.getAll().get(0).getCommodities());
        //int sizeBefore=basket.size();
        assertTrue(basket.size()==1);
        mockMvc.perform(MockMvcRequestBuilders.post("/orders/create"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
        assertTrue(basket.size()==0);
    }
}
