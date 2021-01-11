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
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class LaptopTests {
    
    private Laptop laptop;
    private Brand brand;
    
    @BeforeEach
    public void init(){
        laptop=new Laptop();
        brand=new Brand();
        brand.setName("SomeBrand");
        laptop.setBrand(brand);
        laptop.setModel("SomeModel");
        laptop.setScreenSize(15d);
        laptop.setPrice(15000);
        laptop.setRes(Resolution.QHD);
        laptop.setOperMem(8);
        laptop.setIntMem(500);
    }
    
    @Test
    public void createValidLaptop(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);

        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidIntMems")
    public void laptopWithInvalidIntMem(int input, int errorValue){
        laptop.setIntMem(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidIntMems(){
        return Stream.of(Arguments.of(-1,-1),
                Arguments.of(0,0),
                Arguments.of(-128,-128));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidOperMems")
    public void laptopWithInvalidOperMem(int input, int errorValue){
        laptop.setOperMem(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Laptop>> violations = validator.validate(laptop);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidOperMems(){
        return Stream.of(Arguments.of(-1,-1),
                Arguments.of(0,0),
                Arguments.of(-128,-128));
    }
}
