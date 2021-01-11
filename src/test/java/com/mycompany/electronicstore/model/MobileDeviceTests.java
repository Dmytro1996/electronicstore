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
public class MobileDeviceTests {
    
    private static MobileDevice mobile;    
    private static Brand brand;       
    
    @BeforeEach
    public void init(){
        mobile=new MobileDevice();
        brand=new Brand();
        brand.setName("SomeBrand");
        mobile.setBrand(brand);
        mobile.setModel("Somemodel");
        mobile.setPrice(15000);
        mobile.setScreenSize(5d);
        mobile.setRes(Resolution.QHD);
        mobile.setOperMem(8);
        mobile.setIntMem(128);
        mobile.setExtMem(128);
        mobile.setSimCount(2);
        mobile.setCamera(48);
        mobile.setGps(true);
    }
    
    @Test
    public void createValidMobileDevice(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MobileDevice>> violations = validator.validate(mobile);
        
        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidExtMems")
    public void mobileDeviceWithInvalidExtMem(int input, int errorValue){
        mobile.setExtMem(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MobileDevice>> violations = validator.validate(mobile);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidExtMems(){
        return Stream.of(Arguments.of(-1,-1),
                Arguments.of(-3,-3),
                Arguments.of(-64,-64));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidSimCounts")
    public void mobileDeviceWithInvalidSimCount(int input, int errorValue){
        mobile.setSimCount(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MobileDevice>> violations = validator.validate(mobile);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidSimCounts(){
        return Stream.of(Arguments.of(-1,-1),
                Arguments.of(3,3),
                Arguments.of(5,5));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidExtMems")
    public void mobileDeviceWithInvalidCamera(int input, int errorValue){
        mobile.setCamera(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<MobileDevice>> violations = validator.validate(mobile);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInValidCameras(){
        return Stream.of(Arguments.of(-1,-1),
                Arguments.of(0,0),
                Arguments.of(-64,-64));
    }
}
