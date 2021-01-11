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
public class TelevisionTests {
    
    private static Television tv;
    private static Brand brand;
    
    @BeforeEach
    public void init(){
        tv=new Television();
        brand=new Brand();
        brand.setName("SomeBrand");
        tv.setBrand(brand);
        tv.setModel("KWE4050");
        tv.setPrice(20000);
        tv.setScreenSize(45);
        tv.setRes(Resolution.QHD);
        tv.setSmartTv(true);
        tv.setThreeD(true);
    }
    
    @Test
    public void createValidTv(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Television>> violations = validator.validate(tv);

        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidScreenSizes")
    public void tvWithInvalidScreenSize(int input, int errorValue){
        tv.setScreenSize(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Television>> violations = validator.validate(tv);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidScreenSizes(){
        return Stream.of(Arguments.of(0,0),
                Arguments.of(-1,-1),
                Arguments.of(-45,-45));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidResolutions")
    public void tvWithInvalidResolution(Resolution input, Resolution errorValue){
        tv.setRes(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Television>> violations = validator.validate(tv);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidResolutions(){
        return Stream.of(Arguments.of(null,null));
    }
}
