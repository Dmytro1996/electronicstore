/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.ScreenCommodityModel;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface ScreenCommodityService extends CommodityService {
    
    public default <T extends ScreenCommodityModel> List<T> sortByResolution(
            List<T> comms,String resolution){
        if(resolution!=null && !resolution.equals("All resolutions")){
            comms=comms.stream().filter(t->t.getRes().toString().equals(resolution))
                    .collect(Collectors.toList());
        }
        return comms;
    }
    
    public default <T extends ScreenCommodityModel> List<T> sortByScreenSize(
            List<T> comms,String[] screenSize){
        if(screenSize!=null){
            comms=comms.stream().filter(t->t.getScreenSize()<=Integer.parseInt(screenSize[1])
                    && t.getScreenSize()>=Integer.parseInt(screenSize[0]))
                    .collect(Collectors.toList());
        }
        return comms;
    }
}
