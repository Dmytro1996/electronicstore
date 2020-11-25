/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.AccesorieModel;
import com.mycompany.electronicstore.repository.AccesorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.electronicstore.service.AccesorieService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author dmytr
 */
@Service
public class AccesorieServiceImpl implements AccesorieService{
    
    @Autowired
    private AccesorieRepository accRepo;

    public String getByCriterias(double minPrice,double maxPrice,String brand,String[] name){
        List<AccesorieModel> acc=accRepo.findByPrice(minPrice, maxPrice);
        acc=sortByBrand(acc,brand);
        acc=sortByName(acc,name);
        return toHTML(acc);
    }

    public String getAll(){
        return toHTML(accRepo.findAll());
    }

    private List<AccesorieModel> sortByName(List<AccesorieModel> acc,String[] names){
        if(names==null){return acc;}
        return acc.stream().filter(a->Arrays.asList(names).contains(""+a.getName()))
                .collect(Collectors.toList());
    }    
}
