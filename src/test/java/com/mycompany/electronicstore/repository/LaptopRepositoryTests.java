/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.Laptop;
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
public class LaptopRepositoryTests {
    
    @Autowired
    private LaptopRepository laptopRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByCriteriaTest(){
        Laptop laptop=new Laptop();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        entityManager.persist(brand);
        laptop.setBrand(brand);
        laptop.setPrice(1000);
        laptop.setRes(Resolution.QHD);
        laptop.setModel("UE5500");
        laptop.setScreenSize(40);
        laptop.setOperMem(8);
        laptop.setIntMem(500);
        entityManager.persist(laptop);               
        assertTrue(laptopRepo.findByPrice(999, 1001).stream().map(c->c.toString())
                .anyMatch(c->c.equals(laptop.toString())));
    }
}
