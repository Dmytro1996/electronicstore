/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.electronicstore.auth0;

import com.okta.spring.boot.oauth.AuthoritiesProvider;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author User
 */
@Configuration
public class Auth0AuthoritiesConfig {

    @Bean
    public AuthoritiesProvider customAuthoritiesProvider() {
        return (user, userRequest) -> {
            Set<GrantedAuthority> authorities = new HashSet<>();

            // Map custom roles claim to Spring Security authorities
            Object roles = user.getAttribute("https://electronic-store-api/roles");
            if (roles instanceof List<?> roleList) {
                roleList.forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                );
            }

            return authorities;
        };
    }    
}
