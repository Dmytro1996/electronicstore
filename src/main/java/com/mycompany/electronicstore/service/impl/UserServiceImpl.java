/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.exception.NullEntityReferenceException;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.repository.UserRepository;
import com.mycompany.electronicstore.service.UserService;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepo;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    @Override
    public User create(User user){
        if(user!=null){
            return userRepo.save(user);
        }
        throw new NullEntityReferenceException("User cannot be null");
    }
    
    @Override
    public User readById(long id){
        if(userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        throw new EntityNotFoundException("User with id="+id+" does not exist");
    }
    
    @Override
    public List<User> getAll(){
        return userRepo.findAll().isEmpty()?new ArrayList():userRepo.findAll();
    }
}
