/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore;

import com.mycompany.electronicstore.model.Commodity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author dmytr
 */
@Configuration
public class Config {
    
    @Bean
    @Scope(value=WebApplicationContext.SCOPE_SESSION,
            proxyMode=ScopedProxyMode.TARGET_CLASS)
    public List<Commodity> bakset(){
        return new ArrayList<Commodity>();
    }
    
    @Bean
    public CustomScopeConfigurer scopeConfigurer() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        Map<String, Object> workflowScope = new HashMap<String, Object>();
        workflowScope.put("session", new SimpleThreadScope());
        configurer.setScopes(workflowScope);

        return configurer;
  }
    
}
