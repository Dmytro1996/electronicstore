/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.details.UserDetailsImpl;
import com.mycompany.electronicstore.exception.NullEntityReferenceException;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.repository.UserRepository;
import com.mycompany.electronicstore.service.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dmytr
 */
@Service
public class UserServiceImpl implements UserService,UserDetailsService {
    
    private UserRepository userRepo;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    
    public User create(User user){
        if(user!=null){
            return userRepo.save(user);
        }
        throw new NullEntityReferenceException("User cannot be null");
    }
    
    public User readById(long id){
        if(userRepo.findById(id).isPresent()){
            return userRepo.findById(id).get();
        }
        throw new EntityNotFoundException("User with id="+id+" does not exist");
    }
    
    public List<User> getAll(){
        return userRepo.findAll().isEmpty()?new ArrayList():userRepo.findAll();
    }
    
    @Override
    public UserDetails loadUserByUsername(String userName){        
        Optional<User> user=userRepo.findAll().stream().filter(u->u.getEmail().equals(userName)).findAny();
        if(user.isPresent()){            
            return new UserDetailsImpl(user.get());
        }               
        throw new UsernameNotFoundException("User not found");
    }
}
