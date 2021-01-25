/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.Accesorie;
import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.repository.AccesorieRepository;
import com.mycompany.electronicstore.service.AccesorieService;
import com.mycompany.electronicstore.service.impl.AccesorieServiceImpl;
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
public class AccesorieServiceTests {
     
    @TestConfiguration
    static class AccServiceTestCofig{        
        @Bean
        public AccesorieService accesorieService(){
            return new AccesorieServiceImpl();
        }
    }
    
    @Autowired
    private AccesorieService accService;
    
    @MockBean
    private AccesorieRepository accRepo;
    
    private Accesorie acc;
    
    @BeforeEach
    public void setUp(){
        acc=new Accesorie();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        acc.setBrand(brand);
        acc.setModel("SomeModel");
        acc.setPrice(10000);
        acc.setType("Keyboard");
        acc.setShortDescription("Good keyboard");
        Mockito.when(accRepo.findByPrice(acc.getPrice()-1,acc.getPrice()+1))
                .thenReturn(Arrays.asList(acc));
        Mockito.when(accRepo.findAll())
                .thenReturn(Arrays.asList(acc));
    }
    
    
    @Test
    public void getByCriterisaTest(){
        String expected="<div id=\"commodityRow\"><div class=\""+acc.getClass().getSimpleName()+acc.getId()
                +"\" id=\"commodity\">"+acc.addImage()+acc.toHTML()+"</div></div>";
        assertEquals(expected,accService.getByCriterias(acc.getPrice()-1, acc.getPrice()+1,
                acc.getBrand().getName(),new String[]{String.valueOf(acc.getType())}));
    }
    
    @Test
    public void getAllTest(){
        assertEquals(acc.toString(),accService.getAll().get(0).toString());
    }
    
    @Test
    public void getAllAsHTMLTest(){
        String expected="<div id=\"commodityRow\"><div class=\""+acc.getClass().getSimpleName()+acc.getId()
                +"\" id=\"commodity\">"+acc.addImage()+acc.toHTML()+"</div></div>";
        assertEquals(expected,accService.getAllAsHTML());
    } 
    
    @Test
    public void getTypes(){
        String expected="<div><label>"+acc.getType()
                +"</label><input type=\"checkbox\" name=\"type\" value=\""
                +acc.getType()+"\"></div>";
        assertEquals(expected,accService.getTypes());
    }
}
