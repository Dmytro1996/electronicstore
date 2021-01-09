/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.impl;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.repository.LaptopRepository;
import com.mycompany.electronicstore.service.LaptopService;
import com.mycompany.electronicstore.service.impl.LaptopServiceImpl;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author dmytr
 */
@ExtendWith(SpringExtension.class)
public class LaptopServiceTests {
    
    @TestConfiguration
    static class LaptopServiceTestCofig{        
        @Bean
        public LaptopService laptopService(){
            return new LaptopServiceImpl();
        }
    }
    
    @Autowired
    private LaptopService laptopService;
    
    @MockBean
    private LaptopRepository laptopRepo;
    
    private Laptop laptop;
    
    @BeforeEach
    public void setUp(){
        laptop=new Laptop();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        laptop.setBrand(brand);
        laptop.setModel("SomeModel");
        laptop.setPrice(10000);
        laptop.setRes(Resolution.QHD);
        laptop.setOperMem(8);
        laptop.setIntMem(500);
        Mockito.when(laptopRepo.findByPrice(laptop.getPrice()-1,laptop.getPrice()+1))
                .thenReturn(Arrays.asList(laptop));
        Mockito.when(laptopRepo.findAll())
                .thenReturn(Arrays.asList(laptop));
    }
    
    
    @Test
    public void getByCriterisaTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+laptop.getId()
                +"\" id=\"commodity\">"+laptop.addImage()+laptop.toHTML()+"</div></div>";
        assertEquals(expected,laptopService.getByCriterias(laptop.getPrice()-1, laptop.getPrice()+1,
                String.valueOf(laptop.getScreenSize()), laptop.getBrand().getName(),
                laptop.getRes().toString(), new String[]{String.valueOf(laptop.getOperMem())},
                String.valueOf(laptop.getIntMem())));
    }
    
    @Test
    public void getAllTest(){
        assertEquals(laptop.toString(),laptopService.getAll().get(0).toString());
    }
    
    @Test
    public void getAllAsHTMLTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+laptop.getId()
                +"\" id=\"commodity\">"+laptop.addImage()+laptop.toHTML()+"</div></div>";
        assertEquals(expected,laptopService.getAllAsHTML());
    }
    
    @Test
    public void getOperMemsTest(){
        String expected="<input type=\"checkbox\" name=\"operMem\" value=\""
               +laptop.getOperMem()+"\"><label>"+laptop.getOperMem()+"</label><br>";
        assertEquals(expected,laptopService.getOperMems());
    }
    
    @Test
    public void getIntMemsTest(){
        String expected="<option>"+laptop.getIntMem()+"</option>";
        assertEquals(Arrays.asList(expected),laptopService.getIntMems());
    }
    
    @Test
    public void getBrandsTest(){
        String expected="<option>"+laptop.getBrand().getName()+"</option>";
        assertEquals(Arrays.asList(expected),laptopService.getBrands());
    }
    
    @Test
    public void getScreenSizesTest(){
        String expected="<option>"+laptop.getScreenSize()+"</option>";
        assertEquals(Arrays.asList(expected),laptopService.getScreenSizes());
    }
    
    @Test
    public void getResolutionsTest(){
        String expected="<option>"+laptop.getRes().toString()+"</option>";
        assertEquals(Arrays.asList(expected),laptopService.getResolutions());
    }
}
