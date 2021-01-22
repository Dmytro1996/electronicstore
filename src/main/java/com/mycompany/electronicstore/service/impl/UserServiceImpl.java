/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.repository.UserRepository;
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
public class UserServiceImpl {
    
    private UserRepository userRepo;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    public User create(User user){
        return userRepo.save(user);
    }
    
    public User readById(long id){
        if(userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        throw new EntityNotFoundException("Order with id="+id+" does not exist");
    }
    
    public List<User> getAll(){
        return userRepo.findAll().isEmpty()?new ArrayList():userRepo.findAll();
    }
}
