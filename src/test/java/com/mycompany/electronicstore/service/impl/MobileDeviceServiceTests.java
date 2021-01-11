/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.repository.MobileDeviceRepository;
import com.mycompany.electronicstore.service.MobileDeviceService;
import com.mycompany.electronicstore.service.impl.MobileServiceImpl;
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
public class MobileDeviceServiceTests {
    
    @TestConfiguration
    static class LaptopServiceTestCofig{        
        @Bean
        public MobileDeviceService laptopService(){
            return new MobileServiceImpl();
        }
    }
    
    @Autowired
    private MobileDeviceService mobileService;
    
    @MockBean
    private MobileDeviceRepository mobileRepo;
    
    private MobileDevice mobile;
    
    @BeforeEach
    public void setUp(){
        mobile=new MobileDevice();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        mobile.setBrand(brand);
        mobile.setModel("SomeModel");
        mobile.setPrice(10000);
        mobile.setRes(Resolution.QHD);
        mobile.setOperMem(8);
        mobile.setIntMem(500);
        Mockito.when(mobileRepo.findByPrice(mobile.getPrice()-1,mobile.getPrice()+1))
                .thenReturn(Arrays.asList(mobile));
        Mockito.when(mobileRepo.findAll())
                .thenReturn(Arrays.asList(mobile));
    }
    
    
    @Test
    public void getByCriterisaTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+mobile.getId()
                +"\" id=\"commodity\">"+mobile.addImage()+mobile.toHTML()+"</div></div>";
        assertEquals(expected,mobileService.getByCriterias(mobile.getPrice()-1, mobile.getPrice()+1,
                String.valueOf(mobile.getScreenSize()), mobile.getBrand().getName(),
                new String[]{String.valueOf(mobile.getOperMem())},
                String.valueOf(mobile.getIntMem()),String.valueOf(mobile.getExtMem()),
                String.valueOf(mobile.getCamera()),new String[]{String.valueOf(mobile.getSimCount())},
                new String[]{String.valueOf(mobile.isGps())}));
    }
    
    @Test
    public void getAllTest(){
        assertEquals(mobile.toString(),mobileService.getAll().get(0).toString());
    }
    
    @Test
    public void getAllAsHTMLTest(){
        String expected="<div id=\"commodityRow\"><div class=\"comm"+mobile.getId()
                +"\" id=\"commodity\">"+mobile.addImage()+mobile.toHTML()+"</div></div>";
        assertEquals(expected,mobileService.getAllAsHTML());
    }
        
    @Test
    public void getExtMemsTest(){
        String expected="<option>"+mobile.getExtMem()+"</option>";
        assertEquals(Arrays.asList(expected),mobileService.getExtMems());
    }

    @Test
    public void getCamerasTest(){
        String expected="<option>"+mobile.getCamera()+"</option>";
        assertEquals(Arrays.asList(expected),mobileService.getCameras());
    }    
}
