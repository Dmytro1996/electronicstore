/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Accesorie;
import com.mycompany.electronicstore.model.Brand;
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
public class AccesorieRepositoryTests {
    
    @Autowired
    private AccesorieRepository accRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByCriteriaTest(){
        Accesorie acc=new Accesorie();
        Brand brand=new Brand();
        brand.setName("SomeBrand");
        entityManager.persist(brand);
        acc.setBrand(brand);
        acc.setPrice(1000);        
        acc.setName("Keyboard");
        acc.setShortDescription("Good keyboard");
        entityManager.persist(acc);               
        assertTrue(accRepo.findByPrice(999, 1001).stream().map(c->c.toString())
                .anyMatch(c->c.equals(acc.toString())));
    }
}
