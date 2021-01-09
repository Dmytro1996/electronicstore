/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service;

import com.mycompany.electronicstore.model.Accesorie;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author dmytr
 */
public interface AccesorieService extends CommodityService {
    String getByCriterias(double minPrice,double maxPrice,
           String brand,String[] name);

    public default String getTypes(){
        return getAll().stream().map(c->"<div><label>"+((Accesorie)c).getName()
                +"</label><input type=\"checkbox\" name=\"type\" value=\""
                +((Accesorie)c).getName()+"\"></div>")
                .distinct().sorted().collect(Collectors.joining());
    }    
}
