/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
    @NotNull
    @Pattern(regexp = "^[A-Z]+((\\s?)[a-zA-Z])+",
            message = "Wrong brand name.Must start with a capital letter followed by one or more lowercase letters")
    private String name;
    @OneToMany(mappedBy="brand")
    @JsonBackReference
    private List<Television> tvs;
    @OneToMany(mappedBy="brand")
    @JsonBackReference
    private List<Laptop> laptops;
    @OneToMany(mappedBy="brand")
    @JsonBackReference
    private List<MobileDevice> mobiles;
    @OneToMany(mappedBy="brand")
    @JsonBackReference
    private List<Accesorie> accesories;

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

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public void setMobiles(List<MobileDevice> mobiles) {
        this.mobiles = mobiles;
    }

    public void setAccesories(List<Accesorie> accesories) {
        this.accesories = accesories;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public List<MobileDevice> getMobiles() {
        return mobiles;
    }

    public List<Accesorie> getAccesories() {
        return accesories;
    }

    public void setTvs(List<Television> tvs) {
        this.tvs = tvs;
    }
    
}
