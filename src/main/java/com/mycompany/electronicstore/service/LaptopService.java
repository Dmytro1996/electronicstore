/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.ITCommodity;
import com.mycompany.electronicstore.model.ScreenCommodity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface LaptopService extends ITCommodityService {
    
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] operMem,String intMem);    
    
}
