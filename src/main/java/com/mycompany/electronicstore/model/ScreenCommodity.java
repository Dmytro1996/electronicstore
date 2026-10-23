/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 *
 * @author dmytr
 */
@MappedSuperclass
public abstract class ScreenCommodity extends Commodity{
  @Column(name="screen_size")  
  @Positive
  private double screenSize;
  @Column(name="resolution")
  @NotNull
  @Enumerated(EnumType.STRING)
  private Resolution res;

    public ScreenCommodity() {
        super();
    }
    
    public double getScreenSize() {
        return screenSize;
    }
    public Resolution getRes() {
        return res;
    }
    public void setScreenSize(double screenSize) {
        this.screenSize = screenSize;
    }
    public void setScreenSize(int screenSize){
        this.screenSize = screenSize;
    }
    public void setRes(Resolution res) {
        this.res = res;
    }    
}
