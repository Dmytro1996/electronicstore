/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore;

import com.mycompany.electronicstore.model.Commodity;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author dmytr
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled=true,
        securedEnabled=true,
        jsr250Enabled=true        
)
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/users/create","/acc/**","/tv/**","/laptop/**",
                "/mobile/**","/orders/**","/javascript/**","/images/**","/css/**")
                .permitAll().anyRequest().authenticated().and().formLogin()
                .loginPage("/").loginProcessingUrl("/login")
                .defaultSuccessUrl("/index").failureUrl("/error?userNotFound=true")
                .permitAll().and().logout().logoutUrl("/logout")
                .logoutSuccessUrl("/index").deleteCookies("JSESSIONID");
        http.csrf().disable();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider provider) throws Exception{        
        auth.authenticationProvider(provider);
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    /*@Bean
    @Scope(value=WebApplicationContext.SCOPE_SESSION,
            proxyMode=ScopedProxyMode.TARGET_CLASS)
    public List<Commodity> bakset(){
        return new ArrayList<Commodity>();
    }*/
}
