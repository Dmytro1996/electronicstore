/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.exception.NullEntityReferenceException;
import com.mycompany.electronicstore.model.Order;
import com.mycompany.electronicstore.repository.OrderRepository;
import com.mycompany.electronicstore.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class OrderServiceImpl implements OrderService {
    
    private OrderRepository orderRepo;
    
    @Autowired
    public OrderServiceImpl(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    
    public Order readById(long id){
        if(orderRepo.findById(id).isPresent()){
            return orderRepo.findById(id).get();
        }
        throw new EntityNotFoundException("Order with id="+id+" does not exist");
    }

    public Order create(Order order){
        if(order!=null){
            return orderRepo.save(order);
        }
        throw new NullEntityReferenceException("Order cannot be null");
    }

    public Order delete(long id){
        Order order=readById(id);
        orderRepo.delete(order);
        return order;
    } 
    
    public List<Order> getAll(){
        return orderRepo.findAll().isEmpty()?new ArrayList():orderRepo.findAll();
    }
}
