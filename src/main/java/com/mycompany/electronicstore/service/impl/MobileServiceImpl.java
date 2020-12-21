/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.ITCommodity;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.repository.MobileDeviceRepository;
import com.mycompany.electronicstore.service.MobileDeviceService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class MobileServiceImpl implements MobileDeviceService {
    
    @Autowired
    MobileDeviceRepository mobileRepo;
    Logger logger=LoggerFactory.getLogger(MobileServiceImpl.class);
    
    public String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String[] operMem,String intMem,String extMem,
           String camera,String[] simCount,String[] gps){
        List<MobileDevice> mobiles=mobileRepo.findByPrice(minPrice,maxPrice);
        if(!screenSize.equals("All screensizes")){
            mobiles=sortByScreenSize(mobiles,Double.valueOf(screenSize));}
        mobiles=sortByBrand(mobiles,brand);        
        mobiles=sortByOperMem(mobiles,operMem);
        mobiles=sortByIntMem(mobiles,intMem);
        mobiles=sortByExtMem(mobiles,extMem);
        mobiles=sortByCamera(mobiles,camera);
        mobiles=sortBySimCount(mobiles,simCount);
        mobiles=sortByGps(mobiles,gps);
        return toHTML(mobiles);
    }
    
    public String getByCriterias(double minPrice,double maxPrice,String screenSize,
           String brand,String resolution,String[] operMem,String intMem){               
        return null;
    }
    
    @Override
    public String getAll(){
        return toHTML(mobileRepo.findAll());
    }
    
    private List<MobileDevice> sortByExtMem(List<MobileDevice> mobiles,String extMem){
        if(extMem==null || extMem.equals("All")){return mobiles;}
        return mobiles.stream().filter(m->m.getExtMem()==Integer.parseInt(extMem)).collect(Collectors.toList());
    }
    
    private List<MobileDevice> sortByCamera(List<MobileDevice> mobiles,String camera){
        if(camera==null || camera.equals("All")){return mobiles;}
        return mobiles.stream().filter(m->m.getCamera()==Integer.parseInt(camera))
                .collect(Collectors.toList());
    }
    
    private List<MobileDevice> sortBySimCount(List<MobileDevice> mobiles,String[] simCount){
        if(simCount==null || simCount.equals("Any")){return mobiles;}
        return mobiles.stream().filter(m->m.getSimCount()==Integer.parseInt(simCount[0]))
                .collect(Collectors.toList());
    }
    
    private List<MobileDevice>  sortByGps(List<MobileDevice> mobiles,String[] gps){
        if(gps==null){return mobiles;}
        if(gps.length==1){
          logger.info(""+gps.length);
          logger.info("Length is 1");
          logger.info(""+Boolean.valueOf(gps[0]));
          //if(gps[1].equals(null)){
            mobiles=mobiles.stream().filter(m->(m.isGps()==Boolean.valueOf(gps[0])))
              .collect(Collectors.toList());
          //}            
        }
        return mobiles;
    }
}
