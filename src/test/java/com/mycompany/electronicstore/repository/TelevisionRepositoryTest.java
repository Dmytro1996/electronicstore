/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.model.Television;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author dmytr
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
public class TelevisionRepositoryTest {
    
    @Autowired
    private TelevisionRepository tvRepo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void findByCriteriaTest(){
        Television tv=tvRepo.findById(1L).get();        
        assertEquals(tv.toString(),tvRepo.findByPrice(tv.getPrice()-1, tv.getPrice()+1).get(0).toString());
    }
}
