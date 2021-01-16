/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="laptops")
public class Laptop extends ITCommodity {

    public Laptop() {
        super();
    }
    
    public String toHTML(){
      return "<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"screen\">ScreenSize:"+getScreenSize()+"</p>"+"<p id=\"res\">"+getRes()
              +"</p>"+"<p id=\"operatingMemory\">Operating memory:"+getOperMem()+"</p>"+
              "<p id=\"internalMemory\">Internal memory:"+getIntMem()+"</p>"
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<input type=\"button\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">";
    }    
}
