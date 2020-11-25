/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.Scanner;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

/**
 *
 * @author dmytr
 */
@MappedSuperclass
public abstract class LaptopModel extends ScreenCommodityModel {
  @Column(name="oper_memory")
  private int operMem;
  @Column(name="internal_memory")
  private int intMem;
    public LaptopModel(){
      super();
    }   

    public int getOperMem() {
        return operMem;
    }

    public int getIntMem() {
        return intMem;
    }

    public void setOperMem(int operMem){        
        this.operMem = operMem;
    }

    public void setIntMem(int intMem){        
        this.intMem = intMem;
    }
    public String toString(){
      return getBrand().getName()+" "+getModel()+" Price:"+getPrice()+
              " ScreenSize:"+getScreenSize()+" Resolution:"+
        getRes()+" Operating memory:"+operMem+" Internal memory"+intMem;
    }   
        
}
