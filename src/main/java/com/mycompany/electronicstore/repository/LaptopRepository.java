/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Laptop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author dmytr
 */
@Repository
public interface LaptopRepository extends JpaRepository<Laptop,Long> {    
    
    @Query(value="select * from laptops l where (l.price between :minPrice and :maxPrice)",
            nativeQuery=true)
    public List<Laptop> findByPrice(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice);
}
