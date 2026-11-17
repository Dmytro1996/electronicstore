/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.details;

import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dmytr
 */
public class UserAuthority implements GrantedAuthority {
    
    private String authority;
    
    public UserAuthority(String authority){
        this.authority=authority;        
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    
}
