/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Brand;
import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.model.Television;
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
public class TelevisionRepositoryTest {
    
    @Autowired
    private TelevisionRepository tvRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByCriteriaTest(){
        Television tv=new Television();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        entityManager.persist(brand);
        tv.setBrand(brand);
        tv.setPrice(15000);
        tv.setRes(Resolution.QHD);
        tv.setModel("UE5500");
        tv.setScreenSize(40);
        tv.setSmartTv(true);
        tv.setThreeD(true);
        entityManager.persist(tv);               
        assertTrue(tvRepo.findByPrice(999, 1001).stream().map(c->c.toString())
                .anyMatch(c->c.equals(tv.toString())));
    }
}
