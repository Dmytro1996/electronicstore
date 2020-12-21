/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;
//import electronicstore.exceptions.*;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    public Television(){}
    
    public boolean isThreeD() {
        return threeD;
    }

    public boolean isSmartTv() {
        return smartTv;
    }

    public void setThreeD(boolean threeD) {
        this.threeD = threeD;
    }

    public void setSmartTv(boolean smartTv) {
        this.smartTv = smartTv;
    }
    public String toString(){
      return getId()+" "+getBrand().getName()+" "+getModel()+" Price:"+getPrice()
              +" Screen:"+getScreenSize()+" "+getRes()+"SmartTV:"+isSmartTv()+" 3D:"+isThreeD();
    }

    public String addImage(){
        return "<img src=\"\\images\\tv-"+getBrand().getName().toLowerCase()+
              ".jpg\" alt=\""+getBrand().getName()+" "+getModel()+"\">";
    }

     public String toHTML(){
      return "<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"screen\">ScreenSize:"+getScreenSize()
              +"</p>"+"<p id=\"res\">Resolution:"+getRes()+"</p>"+
              "<p id=\"smart_tv\">SmartTv:"+isSmartTv()+"</p>"+
              "<p id=\"3d\">3D:"+isThreeD()+"</p>"
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              + "<input type=\"button\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">";
    }    
}
