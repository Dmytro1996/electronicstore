/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author dmytr
 */
@SpringBootTest
public class OrderTests {
    
    private static Order order;
    private static User user;
    
    @BeforeEach
    public void init(){
        order=new Order();
        user =new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setEmail("mail@mail.com");
        user.setPassword("q1w2e3r4");        
        order.setUser(user);
        Laptop laptop=new Laptop();
        laptop.setId(1);
        order.setLaptops(Arrays.asList(laptop));
        Television tv=new Television();
        tv.setId(2);
        order.setTvs(Arrays.asList(tv));
        MobileDevice mobile=new MobileDevice();
        mobile.setId(3);
        order.setMobiles(Arrays.asList(mobile));
        Accesorie acc=new Accesorie();
        acc.setId(4);
        order.setAcc(Arrays.asList(acc));
        order.setExecuted(true);        
    }
    
    @Test
    public void createValidOrder(){
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<Order>> violations=validator.validate(order);
        
        assertEquals(order.getUser().getEmail(),user.getEmail());
        assertEquals("1", order.getLaptops().stream().map(c->""+c.getId())
                .collect(Collectors.joining()));
        assertEquals("2", order.getTvs().stream().map(c->""+c.getId())
                .collect(Collectors.joining()));
        assertEquals("3", order.getMobiles().stream().map(c->""+c.getId())
                .collect(Collectors.joining()));
        assertEquals("4", order.getAcc().stream().map(c->""+c.getId())
                .collect(Collectors.joining()));
        assertIterableEquals(Arrays.asList(2L,4L,1L,3L),
                order.getCommodities().stream().map(c->c.getId())
                        .collect(Collectors.toList()));
        assertEquals(order.isExecuted(),true);
        assertEquals(0,violations.size());
    }
    
    @Test
    public void createOrderWithInvalidUser(){
        order.setUser(null);
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<Order>> violations=validator.validate(order);
        
        assertEquals(1,violations.size());
    }
}
