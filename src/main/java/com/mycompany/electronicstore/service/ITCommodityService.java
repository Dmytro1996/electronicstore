/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.ITCommodity;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface ITCommodityService extends ScreenCommodityService {
    
    public default <T extends ITCommodity> List<T> sortByOperMem(List<T> laptops,String[] operMem){
        if(operMem==null){return laptops;}
        return laptops.stream().filter(l->Arrays.asList(operMem).contains(""+l.getOperMem()))
                .collect(Collectors.toList());
    }
    
    public default <T extends ITCommodity> List<T> sortByIntMem(List<T> laptops,String intMem){
        if(intMem==null || intMem.equals("All")){return laptops;}
        return laptops.stream().filter(l->l.getIntMem()==Integer.parseInt(intMem))
                .collect(Collectors.toList());
    }    
    
    public default <T extends ITCommodity> List<T> sortByScreenSize(
            List<T> comms,double screenSize){
        if(screenSize!=0){
            comms=comms.stream().filter(t->t.getScreenSize()==screenSize)
                    .collect(Collectors.toList());
        }
        return comms;
    }
    
    public default <T extends ITCommodity> List<String> getIntMems(){
        return getAll().stream().map(c->"<option>"+((ITCommodity)c).getIntMem()+"</option>")
                .distinct().sorted().collect(Collectors.toList());
    }
    
    public default <T extends ITCommodity> String getOperMems(){
        return getAll().stream().map(c->"<input type=\"checkbox\" name=\"operMem\" value=\""
                +((ITCommodity)c).getOperMem()+"\"><label>"+((ITCommodity)c).getOperMem()+"</label><br>")
                .distinct().sorted().collect(Collectors.joining());
    }
}
