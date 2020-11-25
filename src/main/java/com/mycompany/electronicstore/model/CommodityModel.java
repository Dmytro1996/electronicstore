/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;
//import electronicstore.exceptions.*;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Pattern;
/**
 *
 * @author dmytr
 */
@MappedSuperclass
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class CommodityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;
    @Column
    @Pattern(regexp = "^[A-Z]+((\\s?)(\\w+))+",
            message = "Wrong model.Must start with a capital letter followed by one or more lowercase letters")
    private String model;
    @Column
    private double price;

    public CommodityModel() {}    

    public Brand getBrand() {        
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double getPrice() {
        return price;
    }

    public long getId() {
        return id;
    }    
    
    public void setBrand(Brand brand){        
        this.brand = brand;
    }

    public void setModel(String model){        
        this.model = model;
    }

    public void setPrice(double price){        
        this.price = price;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public abstract String toHTML();
    
    public abstract String addImage();
     
}
