/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.MobileDevice;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public interface MobileDeviceService extends LaptopService {
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,/*String resolution,*/String[] operMem,String intMem,String extMem,
           String camera,String[] simCount,String[] gps);
    //String getAll();
}
