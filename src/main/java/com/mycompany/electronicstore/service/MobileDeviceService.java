/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.MobileDevice;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface MobileDeviceService extends ITCommodityService {
    
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String[] operMem,String intMem,String extMem,
           String camera,String[] simCount,String[] gps);

    public default List<String> getExtMems(){
        return getAll().stream().map(c->"<option>"+((MobileDevice)c).getExtMem()+"</option>")
                .distinct().sorted().collect(Collectors.toList());
    }

    public default List<String> getCameras(){
        return getAll().stream().map(c->"<option>"+((MobileDevice)c).getCamera()+"</option>")
                .distinct().sorted().collect(Collectors.toList());
    }    
}
