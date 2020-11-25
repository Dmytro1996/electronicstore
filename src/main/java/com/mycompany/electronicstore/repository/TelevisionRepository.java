/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Resolution;
import com.mycompany.electronicstore.model.Television;
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
public interface TelevisionRepository extends JpaRepository<Television,Long>{
    
    @Query(value="select * from TV t where t.price between :minPrice and :maxPrice",nativeQuery=true)
    public List<Television> findByPrice(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice);    
}
