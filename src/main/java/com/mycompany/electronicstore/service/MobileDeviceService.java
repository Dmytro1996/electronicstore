/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

/**
 *
 * @author dmytr
 */
public interface MobileDeviceService extends ITCommodityService {
    
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,/*String resolution,*/String[] operMem,String intMem,String extMem,
           String camera,String[] simCount,String[] gps);    
}
