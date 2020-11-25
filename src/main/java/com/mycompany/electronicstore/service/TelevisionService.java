/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.Television;
import java.util.List;

/**
 *
 * @author dmytr
 */
public interface TelevisionService extends ScreenCommodityService {
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] smartTv,String[]  threeD);
    String getAll();   
    
}
