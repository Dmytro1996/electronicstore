/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 *
 * @author dmytr
 */
@SpringBootApplication
//@EnableAutoConfiguration
public class ElectronicStoreApplication extends SpringBootServletInitializer{
    public static void main(String[] args){
        System.out.println("Program runs\n\n\n\n\n\n\n\\n\n\\n\n\n\n\n\n\n\\n\n\n\n\\n\n\n\n");
        SpringApplication.run(ElectronicStoreApplication.class, args);
    }
}
