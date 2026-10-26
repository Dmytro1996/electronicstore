/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;
//import electronicstore.exceptions.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
/**
 *
 * @author dmytr
 */
@MappedSuperclass
public abstract class Commodity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull    
    @ManyToOne
    @JoinColumn(name="brand_id")
    @JsonManagedReference
    private Brand brand;
    @Column
    @Pattern(regexp = "^[A-Z]+((\\s?)(\\w+))+",
            message = "Wrong model.Must start with a capital letter followed by one or more lowercase letters")
    private String model;
    @Column
    @Positive
    private double price;

    public Commodity() {}    

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.brand.getName());
        hash = 67 * hash + Objects.hashCode(this.model);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commodity other = (Commodity) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.brand.getName(), other.brand.getName())) {
            return false;
        }
        return true;
    }
        
    public String addImage(){
         return "<img src=\"\\images\\"+getClass().getSimpleName().toLowerCase()+"-"
                 +getBrand().getName().toLowerCase()+"-"
                 +getModel().toLowerCase()+".jpg\" alt=\""+getBrand().getName()
                 +" "+getModel()+"\">";    
    }
    
    public abstract String toHTML(); 
    
    public abstract List<Order> getOrders();
         
}
