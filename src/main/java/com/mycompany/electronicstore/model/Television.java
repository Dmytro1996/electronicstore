/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;
//import electronicstore.exceptions.*;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/**
 *
 * @author dmytr
 */
@Entity
@Table(name="TV")
public class Television extends ScreenCommodity {
     @Column(name="3D")
     private boolean threeD;      
     @Column(name="smart_tv")
     private boolean smartTv;
     @ManyToMany(mappedBy="tvs")
     private List<Order> orders;

    public Television(){}
    
    public boolean isThreeD() {
        return threeD;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setThreeD(boolean threeD) {
        this.threeD = threeD;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public String toString(){
      return getId()+" "+getBrand().getName()+" "+getModel()+" Price:"+getPrice()
              +" Screen:"+getScreenSize()+" "+getRes()+"SmartTV:"+isSmartTv()+" 3D:"+isThreeD();
    }

    public String toHTML(){
      return "<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"screen\">ScreenSize:"+getScreenSize()
              +"</p>"+"<p id=\"res\">Resolution:"+getRes()+"</p>"+
              "<p id=\"smart_tv\">SmartTv:"+isSmartTv()+"</p>"+
              "<p id=\"3d\">3D:"+isThreeD()+"</p>"
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<form action=\"\\tv\\buy\\"+getId()+"\" method=\"POST\">"
              + "<input type=\"submit\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">"
              + "</form>";
    }    
}
