/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.Commodity;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface CommodityService {
    
    String getAll();
    
    public default <T extends Commodity> List<T> sortByBrand(List<T> comms,String brand){
        if(!brand.equals("All brands")){
            comms=comms.stream().filter(t->t.getBrand().getName().equals(brand))
                    .collect(Collectors.toList());
        }
        return comms;
    }
    
    public default <T extends Commodity> String toHTML(List<T> comms){
        String result="";    
        for(int i=0;i<comms.size();i++){
            if((i==0) || (i%3==0)){result+="<div id=\"commodityRow\">";}
                result+="<div class=\"comm"+comms.get(i).getId()+"\" id=\"commodity\">"+comms.get(i).addImage()+
                comms.get(i).toHTML()+"</div>";
            if(((i+1)%3==0 && i>0) || (i==(comms.size()-1))){result+="</div>";}
       }
        return result;
    }
}
