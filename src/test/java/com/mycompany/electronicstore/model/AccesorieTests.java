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
import static org.junit.Assert.assertTrue;
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
public class AccesorieTests {
    
    private static Accesorie acc;
    private static Brand brand;    
    
    @BeforeEach
    public void init(){
        acc=new Accesorie();
        brand=new Brand();
        brand.setName("SomeBrand");
        acc.setBrand(brand);
        acc.setModel("SomeModel");
        acc.setType("Keyboard");
        acc.setPrice(800);
        acc.setShortDescription("Good keyboard");
    }
    
    @Test
    public void createValidAcccesorie(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Accesorie>> violations = validator.validate(acc);

        assertEquals(0, violations.size());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidTypes")
    public void accWithInvalidType(String input, String errorValue){
        acc.setType(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Accesorie>> violations = validator.validate(acc);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidTypes(){
        return Stream.of(Arguments.of("keyboard","keyboard"),
                Arguments.of("",""),
                Arguments.of(null,null));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidPrices")
    public void accWithInvalidPrice(double input, double errorValue){
        acc.setPrice(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Accesorie>> violations = validator.validate(acc);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidPrices(){
        return Stream.of(Arguments.of(0,0),
                Arguments.of(-1000,-1000),
                Arguments.of(-12500,-12500));
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidBrands")
    public void accWithInvalidBrand(Brand input, Brand errorValue){
        acc.setBrand(input);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Accesorie>> violations = validator.validate(acc);

        assertEquals(1, violations.size());
    }
    
    private static Stream<Arguments> provideInvalidBrands(){
        return Stream.of(Arguments.of(null,null));
    }
    
    @ParameterizedTest
    @MethodSource("provideEqualCommodities")
    public void equalsHahCodeForEqualCommoditiesTest(Commodity input, Commodity errorValue){
        assertTrue(acc.equals(input) && acc.hashCode()==input.hashCode());
    }
    
    private static Stream<Arguments> provideEqualCommodities(){
        Accesorie sameAcc=acc;
        Accesorie accWithSameFields=new Accesorie();
        accWithSameFields.setId(acc.getId());
        accWithSameFields.setBrand(acc.getBrand());
        accWithSameFields.setModel(acc.getModel());
        accWithSameFields.setPrice(acc.getPrice());
        accWithSameFields.setType(acc.getType());
        return Stream.of(Arguments.of(acc,acc),
                Arguments.of(sameAcc, sameAcc),
                Arguments.of(accWithSameFields, accWithSameFields));
    }
    
    @ParameterizedTest
    @MethodSource("provideNotEqualCommodities")
    public void equalsForNotEqualCommoditiesTest(Commodity input, Commodity errorValue){
        assertTrue(!acc.equals(input));        
    }
    
    private static Stream<Arguments> provideNotEqualCommodities(){
        return Stream.of(Arguments.of(null,null),
                Arguments.of(new Accesorie(), new Accesorie()));
    }
    
    @Test
    public void hashCodeForNotEqualCommodities(){
        Accesorie notEqualAcc=new Accesorie();
        notEqualAcc.setId(2);
        notEqualAcc.setBrand(brand);
        notEqualAcc.setModel("model");
        notEqualAcc.setPrice(5000);
        notEqualAcc.setType(acc.getType());
        assertTrue(acc.hashCode()!=notEqualAcc.hashCode());
    }
}
