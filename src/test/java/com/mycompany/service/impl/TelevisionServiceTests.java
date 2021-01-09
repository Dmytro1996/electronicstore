/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.service.impl;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.model.Television;
import com.mycompany.electronicstore.repository.TelevisionRepository;
import com.mycompany.electronicstore.service.TelevisionService;
import com.mycompany.electronicstore.service.impl.TvServiceImpl;
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
public class TelevisionServiceTests {
     
    @TestConfiguration
    static class TelevisionServiceTestCofig{        
        @Bean
        public TelevisionService televisionService(){
            return new TvServiceImpl();
        }
    }
    
    @Autowired
    private TelevisionService tvService;
    
    @MockBean
    private TelevisionRepository tvRepo;
    
    private Television tv;
    
    @BeforeEach
    public void setUp(){
        tv=new Television();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        tv.setBrand(brand);
        tv.setModel("SomeModel");
        tv.setPrice(10000);
        tv.setScreenSize(45);
        tv.setRes(Resolution.QHD);
        tv.setSmartTv(true);
        tv.setThreeD(true);
        Mockito.when(tvRepo.findByPrice(tv.getPrice()-1,tv.getPrice()+1))
                .thenReturn(Arrays.asList(tv));
        Mockito.when(tvRepo.findAll())
                .thenReturn(Arrays.asList(tv));
    }
    
    
    @Test
    public void getByCriterisaTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+tv.getId()
                +"\" id=\"commodity\">"+tv.addImage()+tv.toHTML()+"</div></div>";
        assertEquals(expected,tvService.getByCriterias(tv.getPrice()-1, tv.getPrice()+1,
                "40-50", tv.getBrand().getName(),tv.getRes().toString(),
                new String[]{String.valueOf(tv.isSmartTv())},
                new String[]{String.valueOf(tv.isThreeD())}));
    }
    
    @Test
    public void getAllTest(){
        assertEquals(tv.toString(),tvService.getAll().get(0).toString());
    }
    
    @Test
    public void getAllAsHTMLTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+tv.getId()
                +"\" id=\"commodity\">"+tv.addImage()+tv.toHTML()+"</div></div>";
        assertEquals(expected,tvService.getAllAsHTML());
    }   
        
    @Test
    public void getScreenSizesTest(){
        String expected="<option>40-50</option>";
        assertEquals(Arrays.asList(expected),tvService.getScreenSizes());
    }    
}
