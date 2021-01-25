/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.Laptop;
import com.mycompany.electronicstore.repository.LaptopRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.mycompany.electronicstore.service.LaptopService;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class LaptopServiceImpl implements LaptopService {
    
    @Autowired
    LaptopRepository laptopRepo;
    
    public String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] operMem,String intMem){
        List<Laptop> laptops=laptopRepo.findByPrice(minPrice,maxPrice);
        if(!screenSize.equals("All screensizes")){
            laptops=sortByScreenSize(laptops,Double.valueOf(screenSize));}
        laptops=sortByBrand(laptops,brand);
        laptops=sortByResolution(laptops,resolution);
        laptops=sortByOperMem(laptops,operMem);
        laptops=sortByIntMem(laptops,intMem);
        return toHTML(laptops);
    }
    
    public String getAllAsHTML(){
        return toHTML(laptopRepo.findAll());
    }
    
    public Laptop readById(long id){
        if(laptopRepo.findById(id).isPresent()){
            return laptopRepo.findById(id).get();
        }
        throw new EntityNotFoundException("Laptop wit id="+id+" not found");
    }
    
    public List<Laptop> getAll(){
        return laptopRepo.findAll();
    }
}
