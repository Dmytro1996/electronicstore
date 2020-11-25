/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author dmytr
 */
@MappedSuperclass
public abstract class ScreenCommodityModel extends CommodityModel{
  @Column(name="screen_size")
  private double screenSize;
  @Column(name="resolution")
  @Enumerated(EnumType.STRING)
  private Resolution res;

    public ScreenCommodityModel() {
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
