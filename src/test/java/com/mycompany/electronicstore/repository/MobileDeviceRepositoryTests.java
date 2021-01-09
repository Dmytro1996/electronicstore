/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.MobileDevice;
import com.mycompany.electronicstore.model.Resolution;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author dmytr
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
public class MobileDeviceRepositoryTests {
    @Autowired
    private MobileDeviceRepository mobileRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByCriteriaTest(){
        MobileDevice mobile=new MobileDevice();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        entityManager.persist(brand);
        mobile.setBrand(brand);
        mobile.setPrice(1000);
        mobile.setRes(Resolution.QHD);
        mobile.setModel("UE5500");
        mobile.setScreenSize(5);
        mobile.setOperMem(8);
        mobile.setIntMem(128);
        mobile.setExtMem(128);
        mobile.setCamera(48);
        mobile.setSimCount(2);
        entityManager.persist(mobile);               
        assertTrue(mobileRepo.findByPrice(999, 1001).stream().map(c->c.toString())
                .anyMatch(c->c.equals(mobile.toString())));
    }
}
