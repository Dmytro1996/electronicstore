/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.MobileDevice;
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
public interface MobileDeviceRepository extends JpaRepository<MobileDevice,Long> {
   
    /*@Query(value="select * from mobile_devices m where m.screen_size=:screenSize  and m.brand_id=brandId"
            + "m.resolution=:resolution and m.oper_memory=:operMem and m.internal_memory=:intMem"
            + " and m.external_memory=:extMem and m.camera=:camera and m.gps=gps"
            + " and (m.price between :minPrice and :maxPrice)",nativeQuery=true)
    public List<LaptopModel> findByCriterias(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice,@Param("screen_size")double screenSize,
            @Param("resolution")String resolution,@Param("brandId")int brandId,
            @Param("operMem")int operMem,@Param("intMem")int intMem,
            @Param("extMem")int extMem,@Param("camera")int camera,@Param("gps")boolean gps);*/
    @Query(value="select * from mobile_devices m where (m.price between :minPrice and :maxPrice)",
            nativeQuery=true)
    public List<MobileDevice> findByPrice(@Param("minPrice")double minPrice,
            @Param("maxPrice")double maxPrice);
}
