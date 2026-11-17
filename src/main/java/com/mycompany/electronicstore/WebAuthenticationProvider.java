/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 *
 * @author dmytr
 */
@Component
public class WebAuthenticationProvider implements AuthenticationProvider{
    
    private UserDetailsService userDetailsService;
    Logger logger=LoggerFactory.getLogger(WebAuthenticationProvider.class);
    
    @Autowired
    public void setUserDeatilsService(UserDetailsService userDetailsService){
        this.userDetailsService=userDetailsService;
    }
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{        
        String username=authentication.getName();        
        String password=authentication.getCredentials().toString();
        logger.info("Entered:"+username+" "+password);        
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        logger.info("Found:"+userDetails.getUsername()+" "+userDetails.getPassword());
        //logger.info(userDetails==null);
        //Role role=(Role)userDetails.getAuthorities().toArray()[0];        
        if(userDetails!=null && password.equals(userDetails.getPassword())){            
            return new UsernamePasswordAuthenticationToken(userDetails,
                    userDetails.getPassword(),userDetails.getAuthorities());
        }
        return null;
    }
    
    @Override
    public boolean supports(Class<?> authentication){
        return true;
    }
}
