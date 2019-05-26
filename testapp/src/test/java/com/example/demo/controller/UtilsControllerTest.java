package com.example.demo.controller;


import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.model.Customer;
import com.example.demo.model.Rent;
import com.example.demo.model.RentType;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.RentRepository;
import com.example.demo.repo.RentTypeRepository;
import com.example.demo.utils.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UtilsControllerTest {

	
	@InjectMocks
    private UtilsController utilsController;
	
	
    @Mock
    private RentTypeRepository rentTypeRepository;
    
    @Mock
    private RentRepository rentRepository;
    
    @Mock
    private CustomerRepository customerRepository;
    

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(utilsController).build();
    }

    
    
    
    
    @Test
	public void shouldAddRentType() throws Exception {
    	RentType rentType =  new RentType("Normal Rental", "bike", 5.0, 10.0, 30.0);
    	
    	ResponseEntity<Response<RentType>> response = ResponseEntity.status(200).body(new Response<RentType>("200", "success", rentType));

    	String body = 
		"{" + 
		"	\"name\": \"Normal Rental\"," + 
		"	\"typeProduct\": \"bike\"," + 
		"	\"chargePerHour\": 5," + 
		"	\"chargePerDay\": 10," + 
		"	\"chargePerWeek\": 30" + 
		"}";
    	when(this.utilsController.addRentType(body)).thenReturn(response);
		

        mockMvc.perform(post("/UtilsController/addRentType/")
        .contentType(APPLICATION_JSON)
        .content(body))
        .andExpect(status().is(200));
	}
    
    
    
    @Test
	public void shouldAddCustomer() throws Exception {
    	Customer customer = new Customer("customer.1@gmail.com");
    	Customer savedCustomer = new Customer("customer.1@gmail.com");
    	savedCustomer.setId(1L);

		Mockito.when(customerRepository.save(customer)).thenReturn(savedCustomer);
		
		String body = 
		"{" + 
		"	\"email\": \"customer.1@gmail.com\"" + 
		"}";

        mockMvc.perform(post("/UtilsController/addCustomer/")
        .contentType(APPLICATION_JSON)
        .content(body))
        .andExpect(status().is(200));
	}
    
 
    
    
	@Test
	public void shouldGetAllRentTypes() throws Exception {
		// given
		RentType rentType1 = new RentType("Normal Rental", "bike", 5.0, 10.0, 30.0);
		RentType rentType2 = new RentType(
			"Family Rental",
			"bike",
			rentType1.getChargePerHour() * 0.7,
			rentType1.getChargePerDay() * 0.7,
			rentType1.getChargePerWeek() * 0.7
		);

		
		// when
		when(this.utilsController.getAllRentTypes()).thenReturn(Arrays.asList(rentType1, rentType2));

		// then
		mockMvc.perform(get("/UtilsController/getAllRentTypes/").accept(APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is("Normal Rental")))
				.andExpect(jsonPath("$[0].typeProduct", is("bike")))
				.andExpect(jsonPath("$[0].chargePerHour", is(5.0)))
				.andExpect(jsonPath("$[0].chargePerDay", is(10.0)))
				.andExpect(jsonPath("$[0].chargePerWeek", is(30.0)))
				.andExpect(jsonPath("$[1].name", is("Family Rental")))
				.andExpect(jsonPath("$[1].typeProduct", is("bike")))
				.andExpect(jsonPath("$[1].chargePerHour", is(5.0 * 0.7)))
				.andExpect(jsonPath("$[1].chargePerDay", is(10.0 * 0.7)))
				.andExpect(jsonPath("$[1].chargePerWeek", is(30.0 * 0.7)));

	}
	
	
	
	
	@Test
	public void shouldGetAllCustomers() throws Exception {
		// given
		RentType rentType1 = new RentType("Normal Rental", "bike", 5.0, 10.0, 30.0);
		RentType rentType2 = new RentType(
			"Family Rental",
			"bike",
			rentType1.getChargePerHour() * 0.7,
			rentType1.getChargePerDay() * 0.7,
			rentType1.getChargePerWeek() * 0.7
		);
		
		Customer customer1 = new Customer("customer.1@gmail.com");
		Customer customer2 = new Customer("customer.2@gmail.com");
		
		Rent rent1 = new Rent(rentType1, customer1, 8, "hours");
		Rent rent2 = new Rent(rentType1, customer1, 3, "days");
		Rent rent3 = new Rent(rentType2, customer1, 4, "hours");
		Rent rent4 = new Rent(rentType2, customer1, 2, "days");
		Rent rent5 = new Rent(rentType2, customer1, 1, "weeks");
		
		customer1.addRent(rent1);
		customer1.addRent(rent2);
		
		customer2.addRent(rent3);
		customer2.addRent(rent4);
		customer2.addRent(rent5);
	
		// when
		when(this.utilsController.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

		// then		
		mockMvc.perform(get("/UtilsController/getAllCustomers/").accept(APPLICATION_JSON_UTF8))
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].email", is("customer.1@gmail.com")))
		.andExpect(jsonPath("$[0].listRents[0].time", is(8)))
		.andExpect(jsonPath("$[0].listRents[0].measure", is("hours")))
		.andExpect(jsonPath("$[0].listRents[0].rentType.name", is("Normal Rental")))
		.andExpect(jsonPath("$[0].listRents[0].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[0].listRents[0].rentType.chargePerHour", is(5.0)))
		.andExpect(jsonPath("$[0].listRents[0].rentType.chargePerDay", is(10.0)))
		.andExpect(jsonPath("$[0].listRents[0].rentType.chargePerWeek", is(30.0)))
		.andExpect(jsonPath("$[0].listRents[1].time", is(3)))
		.andExpect(jsonPath("$[0].listRents[1].measure", is("days")))
		.andExpect(jsonPath("$[0].listRents[1].rentType.name", is("Normal Rental")))
		.andExpect(jsonPath("$[0].listRents[1].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[0].listRents[1].rentType.chargePerHour", is(5.0)))
		.andExpect(jsonPath("$[0].listRents[1].rentType.chargePerDay", is(10.0)))
		.andExpect(jsonPath("$[0].listRents[1].rentType.chargePerWeek", is(30.0)))
		.andExpect(jsonPath("$[1].email", is("customer.2@gmail.com")))
		.andExpect(jsonPath("$[1].listRents[0].time", is(4)))
		.andExpect(jsonPath("$[1].listRents[0].measure", is("hours")))
		.andExpect(jsonPath("$[1].listRents[0].rentType.name", is("Family Rental")))
		.andExpect(jsonPath("$[1].listRents[0].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[1].listRents[0].rentType.chargePerHour", is(5.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[0].rentType.chargePerDay", is(10.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[0].rentType.chargePerWeek", is(30.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[1].time", is(2)))
		.andExpect(jsonPath("$[1].listRents[1].measure", is("days")))
		.andExpect(jsonPath("$[1].listRents[1].rentType.name", is("Family Rental")))
		.andExpect(jsonPath("$[1].listRents[1].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[1].listRents[1].rentType.chargePerHour", is(5.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[1].rentType.chargePerDay", is(10.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[1].rentType.chargePerWeek", is(30.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[2].time", is(1)))
		.andExpect(jsonPath("$[1].listRents[2].measure", is("weeks")))
		.andExpect(jsonPath("$[1].listRents[2].rentType.name", is("Family Rental")))
		.andExpect(jsonPath("$[1].listRents[2].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[1].listRents[2].rentType.chargePerHour", is(5.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[2].rentType.chargePerDay", is(10.0 * 0.7)))
		.andExpect(jsonPath("$[1].listRents[2].rentType.chargePerWeek", is(30.0 * 0.7)));
	}
	
	
	
	@Test
	public void shouldGetAllRents() throws Exception {
		// given
		RentType rentType1 = new RentType("Normal Rental", "bike", 5.0, 10.0, 30.0);
		Customer customer1 = new Customer("customer.1@gmail.com");

		Rent rent1 = new Rent(rentType1, customer1, 8, "hours");
		Rent rent2 = new Rent(rentType1, customer1, 3, "days");
	
	
		// when
		when(this.utilsController.getAllRents()).thenReturn(Arrays.asList(rent1, rent2));

		// then		
		mockMvc.perform(get("/UtilsController/getAllRents/").accept(APPLICATION_JSON_UTF8))
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].time", is(8)))
		.andExpect(jsonPath("$[0].measure", is("hours")))
		.andExpect(jsonPath("$[0].rentType.name", is("Normal Rental")))
		.andExpect(jsonPath("$[0].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[0].rentType.chargePerHour", is(5.0)))
		.andExpect(jsonPath("$[0].rentType.chargePerDay", is(10.0)))
		.andExpect(jsonPath("$[0].rentType.chargePerWeek", is(30.0)))
		.andExpect(jsonPath("$[1].time", is(3)))
		.andExpect(jsonPath("$[1].measure", is("days")))
		.andExpect(jsonPath("$[1].rentType.name", is("Normal Rental")))
		.andExpect(jsonPath("$[1].rentType.typeProduct", is("bike")))
		.andExpect(jsonPath("$[1].rentType.chargePerHour", is(5.0)))
		.andExpect(jsonPath("$[1].rentType.chargePerDay", is(10.0)))
		.andExpect(jsonPath("$[1].rentType.chargePerWeek", is(30.0)));
		
	}
	
	
	
	
}