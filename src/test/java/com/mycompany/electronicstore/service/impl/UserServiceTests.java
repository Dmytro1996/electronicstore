/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.service.impl;

import com.mycompany.electronicstore.details.UserDetailsImpl;
import com.mycompany.electronicstore.exception.NullEntityReferenceException;
import com.mycompany.electronicstore.model.Role;
import com.mycompany.electronicstore.model.User;
import com.mycompany.electronicstore.repository.UserRepository;
import com.mycompany.electronicstore.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author dmytr
 */

public class UserServiceTests {
    
    private static UserService userService;
    @MockBean
    private static UserRepository userRepo;
    private static User user;
    
    @BeforeAll
    public static void setUp(){
        userRepo=Mockito.mock(UserRepository.class);
        userService=new UserServiceImpl(userRepo);
        user=new User();
        user.setId(1);
        user.setFirstName("John");
        user.setLastName("Johnson");
        user.setEmail("johnjohnson@mail.com");
        user.setPassword("q1w2e3r4");  
        user.setRole(Role.USER);
        Mockito.when(userRepo.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(userRepo.findById(2L)).thenReturn(Optional.empty());
        Mockito.when(userRepo.findAll()).thenReturn(Arrays.asList(user));
        Mockito.when(userRepo.save(user)).thenReturn(user);                
    }
    
    @Test
    public void readByIdTest(){
        User actualUser=userService.readById(user.getId());
        assertEquals(user.getId(), actualUser.getId());
        assertEquals(user.getEmail(), actualUser.getEmail());        
    }
    
    @Test
    public void readByIdUnexistingIdTest(){
        Throwable e=assertThrows(EntityNotFoundException.class,()->userService.readById(2L));
        assertEquals("User with id=2 does not exist", e.getMessage());
    }
    
    @Test
    public void createTest(){
        User actualUser=userService.create(user);
        assertEquals(user.getId(), actualUser.getId());
        assertEquals(user.getEmail(), actualUser.getEmail());
    }
    
    @Test
    public void createNullUserTest(){
        Throwable e=assertThrows(NullEntityReferenceException.class, ()->userService.create(null));
        assertEquals("User cannot be null", e.getMessage());
    }
    
    @Test
    public void getAllTest(){
        List<User> actualUsers=userService.getAll();
        assertIterableEquals(Arrays.asList(user).stream().map(u->u.getId()).collect(Collectors.toList()),
                actualUsers.stream().map(u->u.getId()).collect(Collectors.toList()));
        assertIterableEquals(Arrays.asList(user).stream().map(u->u.getEmail()).collect(Collectors.toList()),
                actualUsers.stream().map(u->u.getEmail()).collect(Collectors.toList()));
    } 
    
    @Test
    public void loadByUserNameTest(){
        UserDetails actualUser=((UserDetailsService)userService).loadUserByUsername(user.getEmail());
        assertEquals(user.getId(),((UserDetailsImpl)actualUser).getId());
        assertEquals(user.getEmail(),actualUser.getUsername());
    }
    
    @Test
    public void loadByWrongUserNameTest(){        
        Throwable e=assertThrows(UsernameNotFoundException.class, 
                ()->((UserDetailsService)userService).loadUserByUsername("somemail@mail.com"));
        assertEquals("User not found", e.getMessage());
    }
}
