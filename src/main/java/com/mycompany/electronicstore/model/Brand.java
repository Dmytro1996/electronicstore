/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @Pattern(regexp = "^[A-Z]+((\\s?)[a-zA-Z])+",
            message = "Wrong brand name.Must start with a capital letter followed by one or more lowercase letters")
    private String name;
    @OneToMany(mappedBy="brand")
    private List<Television> tvs;
    @OneToMany(mappedBy="brand")
    private List<LaptopModelImpl> laptops;
    @OneToMany(mappedBy="brand")
    private List<MobileDeviceModel> mobiles;
    @OneToMany(mappedBy="brand")
    private List<AccesorieModel> accesories;

    public Brand() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }   

    public List<Television> getTvs() {
        return tvs;
    }

    public void setLaptops(List<LaptopModelImpl> laptops) {
        this.laptops = laptops;
    }

    public void setMobiles(List<MobileDeviceModel> mobiles) {
        this.mobiles = mobiles;
    }

    public void setAccesories(List<AccesorieModel> accesories) {
        this.accesories = accesories;
    }

    public List<LaptopModelImpl> getLaptops() {
        return laptops;
    }

    public List<MobileDeviceModel> getMobiles() {
        return mobiles;
    }

    public List<AccesorieModel> getAccesories() {
        return accesories;
    }

    public void setTvs(List<Television> tvs) {
        this.tvs = tvs;
    }
    
}
