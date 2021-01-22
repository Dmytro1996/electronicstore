/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.repository;

import com.mycompany.electronicstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dmytr
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    
}
