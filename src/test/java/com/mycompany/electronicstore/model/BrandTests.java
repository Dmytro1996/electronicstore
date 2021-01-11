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
public class BrandTests {
   
    private static Brand brand;
    
    @BeforeEach
    public void init(){
        brand=new Brand();
        brand.setId(1);
        brand.setName("SomeBrand");
    }
    
    @Test
    public void createValidBrand(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Brand>> violations = validator.validate(brand);

        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidNames")
    public void brandWithInvalidName(String input, String errorValue){
        brand.setName(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Brand>> violations = validator.validate(brand);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidNames(){
        return Stream.of(Arguments.of("",""),
                Arguments.of(null,null),
                Arguments.of("someBrand","someBrand"),
                Arguments.of("12345AbCd#","12345AbCd#"));
    }
}
