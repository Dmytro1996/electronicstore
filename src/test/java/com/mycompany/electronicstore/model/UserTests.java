/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.electronicstore.model;

import java.util.Set;
import java.util.stream.Stream;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author dmytr
 */
@SpringBootTest
public class UserTests {
    
    private static User user;
    
    @BeforeEach
    public void init(){
        user=new User();
        user.setFirstName("Firstname");
        user.setLastName("Lastname");
        user.setEmail("mail@mail.com");
        user.setPassword("q1w2e3r4");
        user.setPhone("380123456789");
    }
    
    @Test
    public void createValidUser(){
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<User>> violations=validator.validate(user);
        
        assertEquals("Firstname", user.getFirstName());
        assertEquals("Lastname", user.getLastName());
        assertEquals("mail@mail.com", user.getEmail());
        assertEquals("q1w2e3r4", user.getPassword());
        assertEquals("380123456789", user.getPhone());
        assertEquals(0, violations.size()); 
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    public void createUserWithInvalidName(String input, String errorValue){
        user.setFirstName(input);
        user.setLastName(input);
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<User>> violations=validator.validate(user);
        
        assertEquals(2, violations.size());
    }
    
    public static Stream<Arguments> provideInvalidNames(){
        return Stream.of(Arguments.of("john", "john"),
                Arguments.of("JoHn", "JoHn"),                
                Arguments.of("", ""),
                Arguments.of(null, null));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidEmails")
    public void createUserWithInvalidEmail(String input, String errorValue){
        user.setEmail(input);
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<User>> violations=validator.validate(user);
        
        assertEquals(1, violations.size());
    }
    
    public static Stream<Arguments> provideInvalidEmails(){
        return Stream.of(Arguments.of("mail@mail", "mail@mail"),
                Arguments.of("@mail", "@mail"),
                Arguments.of("mail", "mail"),
                Arguments.of("", ""),
                Arguments.of(null, null));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidPasswords")
    public void createUserWithInvalidPassword(String input, String errorValue){
        user.setEmail(input);
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<User>> violations=validator.validate(user);
        
        assertEquals(1, violations.size());
    }
    
    public static Stream<Arguments> provideInvalidPasswords(){
        return Stream.of(Arguments.of("", ""),
                Arguments.of(null, null),
                Arguments.of("aaaaaaaa", "aaaaaaaa"),
                Arguments.of("111111", "111111"),
                Arguments.of("a1a1", "a1a1"),
                Arguments.of("a1a1@", "a1a1@"));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidPhones")
    public void createUserWithInvalidPhone(String input, String errorValue){
        user.setEmail(input);
        ValidatorFactory factory=Validation.buildDefaultValidatorFactory();
        Validator validator=factory.getValidator();
        Set<ConstraintViolation<User>> violations=validator.validate(user);
        
        assertEquals(1, violations.size());
    }
    
    public static Stream<Arguments> provideInvalidPhones(){
        return Stream.of(Arguments.of("", ""),
                Arguments.of(null, null),
                Arguments.of("aaaaaaaa", "aaaaaaaa"),
                Arguments.of("111111", "111111"),
                Arguments.of("+12345", "+12345"),
                Arguments.of("+38012a3456789", "+38012a3456789"));
    }
    
}
