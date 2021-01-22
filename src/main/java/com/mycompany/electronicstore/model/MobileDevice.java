/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="mobile_devices")
public class MobileDevice extends ITCommodity {
    @Column(name="external_memory")
    @PositiveOrZero
    private int extMem;
    @Column(name="sim_count")
    @Min(0)
    @Max(2)
    private int simCount;
    @Column
    @Positive
    private int camera;
    @Column
    private boolean gps;
    @ManyToMany(mappedBy="mobiles")
    private List<Order> orders;
    
    public MobileDevice(){
        super();
    } 

    public int getExtMem() {
        return extMem;
    }

    public int getSimCount() {
        return simCount;
    }

    public int getCamera() {
        return camera;
    }

    public boolean isGps() {
        return gps;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setExtMem(int extMem){       
        this.extMem = extMem;
    }

    public void setSimCount(int simCount){        
        this.simCount = simCount;
    }

    public void setCamera(int camera){        
        this.camera = camera;
    }

    public void setGps(boolean gps) {
        this.gps = gps;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
    public String toString(){
      return getBrand().getName()+" "+getModel()+" Price: "+getPrice()+
              " ScreenSize:"+getScreenSize()+" Resolution:"+getRes()+
              " Operating memory:"+getOperMem()+" Internal memory"+getIntMem()+
              " External memory:"+extMem+" Sim cards"+simCount+" Camera"+camera+" GPS"+gps;
    }
    
    public String toHTML(){
      return "<p id=\"brandModel\">"+getBrand().getName()+" "+getModel()+"</p>"
              +"<p id=\"screen\">ScreenSize:"+getScreenSize()+"</p>"+"<p id=\"res\">"+getRes()
              +"</p>"+"<p id=\"operatingMemory\">Operating memory:"+getOperMem()+"</p>"+
              "<p id=\"internalMemory\">Internal memory:"+getIntMem()+"</p>"+
              "<p id=\"externalMemory\">External memory:"+getExtMem()+"</p>"+
              "<p id=\"cameraOutput\">Camera:"+getCamera()+"</p>"+
              "<p id=\"sim\">Sim cards:"+getSimCount()+"</p>"+
              "<p id=\"gpsOutput\">Gps:"+isGps()+"</p>"
              +"<p id=\"price\">Price:"+getPrice()+"</p>" 
              +"<form action=\"\\mobile\\buy\\"+getId()+"\" method=\"POST\">"
              + "<input type=\"submit\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">"
              + "</form>";
    }    
}
