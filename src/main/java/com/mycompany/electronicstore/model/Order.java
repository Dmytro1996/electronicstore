/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="Orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column
    private boolean executed;
    @ManyToMany
    @JoinTable(name="tv_ordered",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="tv_id"))
    private List<Television> tvs;
    @ManyToMany
    @JoinTable(name="laptops_ordered",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="laptop_id"))
    private List<Laptop> laptops;
    @ManyToMany
    @JoinTable(name="mobiles_ordered",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="mobile_id"))
    private List<MobileDevice> mobiles;
    @ManyToMany
    @JoinTable(name="acc_ordered",
            joinColumns=@JoinColumn(name="order_id"),
            inverseJoinColumns=@JoinColumn(name="acc_id"))
    private List<Accesorie> acc;

    public Order() {
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public boolean isExecuted() {
        return executed;
    }

    public List<Television> getTvs() {
        return tvs;
    }

    public List<Laptop> getLaptops() {
        return laptops;
    }

    public List<MobileDevice> getMobiles() {
        return mobiles;
    }

    public List<Accesorie> getAcc() {
        return acc;
    }
    
    public List<Commodity> getCommodities(){
        List<Commodity> comms=new ArrayList(tvs);
        comms.addAll(acc);
        comms.addAll(laptops);
        comms.addAll(mobiles);
        return comms;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public void setTvs(List<Television> tvs) {
        this.tvs = tvs;
    }

    public void setLaptops(List<Laptop> laptops) {
        this.laptops = laptops;
    }

    public void setMobiles(List<MobileDevice> mobiles) {
        this.mobiles = mobiles;
    }

    public void setAcc(List<Accesorie> acc) {
        this.acc = acc;
    }
    
}
