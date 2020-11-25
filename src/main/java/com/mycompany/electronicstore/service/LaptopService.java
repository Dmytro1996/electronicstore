/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.LaptopModel;
import com.mycompany.electronicstore.model.ScreenCommodityModel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface LaptopService extends ScreenCommodityService {
    String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] operMem,String intMem);
    String getAll();
    
    public default <T extends LaptopModel> List<T> sortByOperMem(List<T> laptops,String[] operMem){
        if(operMem==null){return laptops;}
        return laptops.stream().filter(l->Arrays.asList(operMem).contains(""+l.getOperMem()))
                .collect(Collectors.toList());
    }
    
    public default <T extends LaptopModel> List<T> sortByIntMem(List<T> laptops,String intMem){
        if(intMem==null || intMem.equals("All")){return laptops;}
        return laptops.stream().filter(l->l.getIntMem()==Integer.parseInt(intMem))
                .collect(Collectors.toList());
    }    
    
    public default <T extends LaptopModel> List<T> sortByScreenSize(
            List<T> comms,double screenSize){
        if(screenSize!=0){
            comms=comms.stream().filter(t->t.getScreenSize()==screenSize)
                    .collect(Collectors.toList());
        }
        return comms;
    }
}
