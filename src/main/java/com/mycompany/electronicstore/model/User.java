/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author dmytr
 */
@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern(regexp = "[A-Za-z0-9 ]+",
            message = "Must contain lowercase, uppercase letters, numbers or spaces")
    private String firstName;
    @Pattern(regexp = "[A-Za-z0-9 ]+",
            message = "Must contain lowercase, uppercase letters, numbers or spaces")
    private String lastName;
    @NotNull
    @Pattern(regexp = "[A-Za-z0-9 ]+",
            message = "Must contain lowercase, uppercase letters, numbers or spaces.")
    private String name;
    @NotNull
    @Pattern(regexp = "[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}", message = "Must be a valid e-mail address")
    @Column(unique = true)
    private String email;
    @Pattern(regexp = "\\+?[0-9]{10,12}", message = "Must be a valid phone number")
    private String phone;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
