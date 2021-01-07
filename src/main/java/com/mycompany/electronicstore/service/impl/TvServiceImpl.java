/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.Television;
import com.mycompany.electronicstore.repository.TelevisionRepository;
import com.mycompany.electronicstore.service.TelevisionService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class TvServiceImpl implements TelevisionService {
    
    @Autowired
    TelevisionRepository tvRepo;
    
    public String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] smartTv,String[]  threeD){
        List<Television> tvs=tvRepo.findByPrice(minPrice,maxPrice);                
        tvs=sortByBrand(tvs,brand);
        if(!screenSize.equals("All screensizes")){
            tvs=sortByScreenSize(tvs,screenSize.split("-"));}
        tvs=sortByResolution(tvs,resolution);
        tvs=sortBySmartTv(tvs,smartTv);
        tvs=sortByThreeD(tvs,threeD);
        return toHTML(tvs);
    }
    
    public String getAllAsHTML(){
        return toHTML(tvRepo.findAll());
    }
    
    public List<Television> getAll(){
        return tvRepo.findAll();
    }
    
    private List<Television> sortBySmartTv(List<Television> tvs,String[] smartTv){
        if(smartTv==null){return tvs;}
        if(smartTv.length==1){         
            tvs=tvs.stream().filter(m->(m.isSmartTv()==Boolean.valueOf(smartTv[0])))
              .collect(Collectors.toList());                     
        }
        return tvs;
    }
    
    private List<Television> sortByThreeD(List<Television> tvs,String[] threeD){
        if(threeD==null){return tvs;}
        if(threeD.length==1){         
            tvs=tvs.stream().filter(m->(m.isThreeD()==Boolean.valueOf(threeD[0])))
              .collect(Collectors.toList());                     
        }
        return tvs;
    }
}
