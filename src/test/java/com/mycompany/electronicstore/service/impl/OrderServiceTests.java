/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.exception.NullEntityReferenceException;
import com.mycompany.electronicstore.model.Order;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.repository.OrderRepository;
import com.mycompany.electronicstore.service.OrderService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author dmytr
 */
public class OrderServiceTests {
    
    private static OrderService orderService;
    @MockBean
    private static OrderRepository orderRepo;
    private static Order order;
    
    @BeforeAll
    public static void setUp(){
        orderRepo=Mockito.mock(OrderRepository.class);
        orderService=new OrderServiceImpl(orderRepo);
        order=new Order();
        order.setId(1);
        order.setExecuted(true);
        order.setUserId("12345");
        Mockito.when(orderRepo.findById(1L)).thenReturn(Optional.of(order));
        Mockito.when(orderRepo.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(orderRepo.findAll()).thenReturn(Arrays.asList(order));
        Mockito.when(orderRepo.save(order)).thenReturn(order);
    }
    
    @Test
    public void createTest(){
        Order actualOrder=orderService.readById(order.getId());
        assertEquals(order.getId(), actualOrder.getId());
    }
    
    @Test
    public void createNullTest(){
        Throwable e=assertThrows(NullEntityReferenceException.class, ()->orderService.create(null));
        assertEquals("Order cannot be null", e.getMessage());
    }
    
    @Test
    public void readByIdTest(){
        Order actualOrder=orderService.readById(order.getId());
        assertEquals(order.getId(), actualOrder.getId());
    }
    
    @Test
    public void readByIdWrongIdTest(){
        Throwable e=assertThrows(EntityNotFoundException.class, ()->orderService.readById(2L));
        assertEquals("Order with id=2 does not exist", e.getMessage());
    }
    
    @Test
    public void getAllTest(){
        List<Order> actualOrders=orderService.getAll();
        assertIterableEquals(Arrays.asList(order).stream().map(o->o.getId())
                .collect(Collectors.toList()), 
                actualOrders.stream().map(o->o.getId()).collect(Collectors.toList()));
    }
    
    @Test
    public void deleteTest(){
        Order actualOrder=orderService.delete(order.getId());
        assertEquals(order.getId(), actualOrder.getId());
    }    
}
