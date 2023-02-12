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
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
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
    
    @Autowired
    ClientRegistrationRepository clientRegistrationRepository;
    @Autowired
    private Environment env;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests().antMatchers("/users/create","/index","/acc/**",
                "/tv/**","/laptop/**","/mobile/**",/*"/orders/**",*/"/javascript/**",
                "/images/**","/css/**", "/").permitAll().anyRequest().authenticated()
                .and().formLogin().loginPage("/")//.loginProcessingUrl("/login")
                .defaultSuccessUrl("/index")/*.failureUrl("/index?userNotFound=true")
                .permitAll()*/.and().logout().logoutSuccessHandler(oidcLogoutSuccessHandler())//.logoutUrl("/logout")
                //.logoutSuccessUrl("/index").invalidateHttpSession(true)
                //.clearAuthentication(true).deleteCookies()
                .and().oauth2Login();
        http.csrf().disable();
    }
    
    private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler(){
        OidcClientInitiatedLogoutSuccessHandler successHandler=
                new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
        successHandler.setPostLogoutRedirectUri("http://localhost:"+env.getProperty("server.port"));
        return successHandler;
    }
}
