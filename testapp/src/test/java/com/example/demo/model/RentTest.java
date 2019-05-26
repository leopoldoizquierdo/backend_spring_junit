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


public class RentTest {
	
	private static final RentType A_VALID_RENT_TYPE = new RentType(
		"Normal Rental", "bike", 5.0, 10.0, 30.0
	);
	private static final Customer A_VALID_CUSTOMER = new Customer(
		"customer.1@gmail.com"
	);
	private static final int A_VALID_TIME = 4;
    private static final String A_VALID_MEASURE = "days";
    private static final String AN_EMPTY_STRING = "";
    private static final Object A_NULL_VALUE = null;
    private static final int A_NEGATIVE_VALUE = -1;
    
    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    
    @Test
    public void rent_type_should_not_be_null() throws Exception {
        Rent rent = new Rent(
    		(RentType)A_NULL_VALUE, 
    		A_VALID_CUSTOMER,
    		A_VALID_TIME, 
    		A_VALID_MEASURE
		);
        Set<ConstraintViolation<Rent>> violations = validator.validate(rent);
        assertThat(violations, is(not(empty())));
    }
    
    @Test
    public void customer_should_not_be_null() throws Exception {
        Rent rent = new Rent(
    		A_VALID_RENT_TYPE, 
    		(Customer)A_NULL_VALUE,
    		A_VALID_TIME, 
    		A_VALID_MEASURE
		);
        Set<ConstraintViolation<Rent>> violations = validator.validate(rent);
        assertThat(violations, is(not(empty())));
    }
    
    
    @Test
    public void time_should_not_be_a_negative_value() throws Exception {
        Rent rent = new Rent(
    		A_VALID_RENT_TYPE, 
    		A_VALID_CUSTOMER,
    		A_NEGATIVE_VALUE, 
    		A_VALID_MEASURE
		);
        Set<ConstraintViolation<Rent>> violations = validator.validate(rent);
        assertThat(violations, is(not(empty())));
    }
    
    
    @Test
    public void measure_should_not_be_empty() throws Exception {
        Rent rent = new Rent(
    		A_VALID_RENT_TYPE, 
    		A_VALID_CUSTOMER,
    		A_VALID_TIME, 
    		AN_EMPTY_STRING
		);
        Set<ConstraintViolation<Rent>> violations = validator.validate(rent);
        assertThat(violations, is(not(empty())));
    }
    
    
    @Test
    public void a_valid_rent_should_not_have_constraints_violations() throws Exception {
        Rent rent = new Rent(
    		A_VALID_RENT_TYPE, 
    		A_VALID_CUSTOMER,
    		A_VALID_TIME, 
    		A_VALID_MEASURE
		);
        Set<ConstraintViolation<Rent>> violations = validator.validate(rent);
        assertThat(violations, is(empty()));
    }
}


















