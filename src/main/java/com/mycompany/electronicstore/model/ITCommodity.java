/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Positive;

/**
 *
 * @author dmytr
 */
@MappedSuperclass
public abstract class ITCommodity extends ScreenCommodity {
  @Column(name="oper_memory")
  @Positive
  private int operMem;
  @Column(name="internal_memory")
  @Positive
  private int intMem;
    public ITCommodity(){
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
