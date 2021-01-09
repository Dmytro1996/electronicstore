/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.ScreenCommodity;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface ScreenCommodityService extends CommodityService {
    
    public default <T extends ScreenCommodity> List<T> sortByResolution(
            List<T> comms,String resolution){
        if(resolution!=null && !resolution.equals("All resolutions")){
            comms=comms.stream().filter(t->t.getRes().toString().equals(resolution))
                    .collect(Collectors.toList());
        }
        return comms;
    }
    
    public default <T extends ScreenCommodity> List<T> sortByScreenSize(
            List<T> comms,String[] screenSize){
        if(screenSize!=null){
            comms=comms.stream().filter(t->t.getScreenSize()<=Integer.parseInt(screenSize[1])
                    && t.getScreenSize()>=Integer.parseInt(screenSize[0]))
                    .collect(Collectors.toList());
        }
        return comms;
    }
    
    public default List<String> getScreenSizes(){
        return getAll().stream().map(c->(int)((ScreenCommodity)c).getScreenSize())
                .map(c->(c/10)*10).map(c->"<option>"+c+"-"+(c+10)+"</option>")
                .distinct().sorted().collect(Collectors.toList());
    }
    
    public default List<String> getResolutions(){
        return getAll().stream().map(c->"<option>"+((ScreenCommodity)c).getRes()+"</option>")
                .distinct().sorted().collect(Collectors.toList());
    }
}
