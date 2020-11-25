/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.LaptopModelImpl;
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
public interface LaptopRepository extends JpaRepository<LaptopModelImpl,Long> {
    
    /*@Query(value="select * from laptops l where l.screen_size=:screenSize  and l.brand_id=brandId"
            + "l.resolution=:resolution and l.oper_memory=:operMem and l.internal_memory=:intMem"
            + " and (l.price between :minPrice and :maxPrice)",nativeQuery=true)
    public List<LaptopModel> findByCriterias(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice,@Param("screen_size")double screenSize,
            @Param("resolution")String resolution,@Param("brandId")int brandId,
            @Param("operMem")int operMem,@Param("intMem")int intMem);*/
    @Query(value="select * from laptops l where (l.price between :minPrice and :maxPrice)",
            nativeQuery=true)
    public List<LaptopModelImpl> findByPrice(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice);
}
