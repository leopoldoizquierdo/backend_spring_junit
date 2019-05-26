package com.example.demo.model;


import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class RentTypeTest {
	
	private static final String A_VALID_NAME = "Normal Rental";
    private static final String A_VALID_TYPE_PRODUCT = "bike";
    private static final double A_VALID_CHARGE_PER_HOUR = 5.0;
    private static final double A_VALID_CHARGE_PER_DAY = 10.0;
    private static final double A_VALID_CHARGE_PER_WEEK = 30.0;
    private static final String AN_EMPTY_STRING = "";
    private static final double A_NEGATIVE_VALUE = -1;

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    
    @Test
    public void name_should_not_be_empty() throws Exception {
        RentType rentType = new RentType(
    		AN_EMPTY_STRING, 
    		A_VALID_TYPE_PRODUCT,
    		A_VALID_CHARGE_PER_HOUR, 
    		A_VALID_CHARGE_PER_DAY, 
    		A_VALID_CHARGE_PER_WEEK
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(not(empty())));
    }
    
    @Test
    public void type_product_should_not_be_empty() throws Exception {
        RentType rentType = new RentType(
    		A_VALID_NAME, 
    		AN_EMPTY_STRING,
    		A_VALID_CHARGE_PER_HOUR, 
    		A_VALID_CHARGE_PER_DAY, 
    		A_VALID_CHARGE_PER_WEEK
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(not(empty())));
    }
    
    @Test
    public void carge_per_hour_should_not_be_negative() throws Exception {
        RentType rentType = new RentType(
    		A_VALID_NAME, 
    		A_VALID_TYPE_PRODUCT,
    		A_NEGATIVE_VALUE, 
    		A_VALID_CHARGE_PER_DAY, 
    		A_VALID_CHARGE_PER_WEEK
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(not(empty())));
    }
    
    @Test
    public void carge_per_day_should_not_be_negative() throws Exception {
        RentType rentType = new RentType(
    		A_VALID_NAME, 
    		A_VALID_TYPE_PRODUCT,
    		A_VALID_CHARGE_PER_HOUR, 
    		A_NEGATIVE_VALUE, 
    		A_VALID_CHARGE_PER_WEEK
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(not(empty())));
    }
    
    @Test
    public void carge_per_week_should_not_be_negative() throws Exception {
        RentType rentType = new RentType(
    		A_VALID_NAME, 
    		A_VALID_TYPE_PRODUCT,
    		A_VALID_CHARGE_PER_HOUR, 
    		A_VALID_CHARGE_PER_DAY, 
    		A_NEGATIVE_VALUE
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(not(empty())));
    }
    
    
    @Test
    public void a_valid_rent_type_should_not_have_constraints_violations() throws Exception {
    	RentType rentType = new RentType(
    		A_VALID_NAME, 
    		A_VALID_TYPE_PRODUCT,
    		A_VALID_CHARGE_PER_HOUR, 
    		A_VALID_CHARGE_PER_DAY, 
    		A_VALID_CHARGE_PER_WEEK
		);
        Set<ConstraintViolation<RentType>> violations = validator.validate(rentType);
        assertThat(violations, is(empty()));
    }

}























