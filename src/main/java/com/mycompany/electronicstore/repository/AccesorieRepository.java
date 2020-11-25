/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.AccesorieModel;
import com.mycompany.electronicstore.model.LaptopModel;
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
public interface AccesorieRepository extends JpaRepository<AccesorieModel,Long> {
    
    /*@Query(value="select * from Accesories a where a.brand_id=brandId and a.name=:name"
             + " and (m.price between :minPrice and :maxPrice)",nativeQuery=true)
    public List<LaptopModel> findByCriterias(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice,@Param("brandId")int brandId,@Param("name")String name);*/
    @Query(value="select * from Accesories a where (a.price between :minPrice and :maxPrice)",
            nativeQuery=true)
    public List<AccesorieModel> findByPrice(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice);
}
