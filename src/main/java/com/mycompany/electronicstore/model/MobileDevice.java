/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

/*import electronicstore.exceptions.BooleanException;
import electronicstore.exceptions.NumericValueException;
import electronicstore.exceptions.ResolutionException;
import electronicstore.exceptions.StringFieldException;
import electronicstore.exceptions.SimCountException;*/
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="mobile_devices")
public class MobileDevice extends ITCommodity {
    @Column(name="external_memory")
    private int extMem;
    @Column(name="sim_count")
    private int simCount;
    @Column
    private int camera;
    @Column
    private boolean gps;
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
              + "<input type=\"button\" id=\"comm"+getId()+"\" class=\"buy\" value=\"Buy\">";
    }
    
    public String addImage(){
    if(getBrand().getName().equals("Samsung") || getBrand().getName().equals("Apple"))
      return "<img src=\"\\images\\mobileDevice-"+getBrand().getName().toLowerCase()+"-"
            +getModel().toLowerCase()+".jpg\" alt=\""+getBrand().getName()
              +" "+getModel()+"\">";
    if(getBrand().equals("Huawei") || getBrand().getName().equals("Meizu") 
            || getBrand().getName().equals("Xiaomi")){
      return "<img src=\"\\images\\mobileDevice-"+getBrand().getName().toLowerCase()+
              ".jpg\" alt=\""+getBrand().getName()+" "+getModel()+"\">";
    }
    return "<img src=\"\\images\\mobileDevice-smartphone.jpg\" alt=\""+getBrand().getName()
            +" "+getModel()+"\">";
  }
}
