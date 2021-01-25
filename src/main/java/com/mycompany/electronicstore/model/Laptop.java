/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="laptops")
public class Laptop extends ITCommodity {
    
    @ManyToMany(mappedBy="laptops")
    private List<Order> orders;
    
    public Laptop() {
        super();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public String toHTML(){
      return "<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"screen\">ScreenSize:"+getScreenSize()+"</p>"+"<p id=\"res\">"+getRes()
              +"</p>"+"<p id=\"operatingMemory\">Operating memory:"+getOperMem()+"</p>"+
              "<p id=\"internalMemory\">Internal memory:"+getIntMem()+"</p>"
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<form action=\"\\laptop\\buy\\"+getId()+"\" method=\"POST\">"
              + "<input type=\"submit\" id=\""+getClass().getSimpleName()+getId()
              +"\" class=\"buy\" value=\"Buy\">"+ "</form>";
    }    
}
