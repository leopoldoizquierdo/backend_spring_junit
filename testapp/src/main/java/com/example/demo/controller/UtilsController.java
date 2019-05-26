package com.example.demo.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.Rent;
import com.example.demo.model.RentType;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.RentRepository;
import com.example.demo.repo.RentTypeRepository;
import com.example.demo.utils.Response;


@RestController
@RequestMapping(value = "/UtilsController")
public class UtilsController {
	
	@Autowired
    protected CustomerRepository customerRepository;
	
	@Autowired
    protected RentRepository rentRepository;
	
	@Autowired
    protected RentTypeRepository rentTypeRepository;



	@RequestMapping(value = "/addRentType", method = RequestMethod.POST)
    public ResponseEntity<Response<RentType>> addRentType(@RequestBody String body) {
		try {
			JSONObject joBody = new JSONObject(body);
			RentType rentType = new RentType(
				joBody.getString("name"),
				joBody.getString("typeProduct"),
				joBody.getDouble("chargePerHour"),
				joBody.getDouble("chargePerDay"),
				joBody.getDouble("chargePerWeek")
			);
			rentType = rentTypeRepository.save(rentType);
			return ResponseEntity.status(200).body(new Response<RentType>("200", "success", rentType));
		}
		catch(Exception e){
			return ResponseEntity.status(500).body(new Response<RentType>("500", e.getCause().toString(), null));
		}
    }
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public ResponseEntity<Response<Customer>> addCustomer(@RequestBody String body) {
		try {
			JSONObject joBody = new JSONObject(body);
			Customer customer = new Customer(
				joBody.getString("email")
			);
			customer = customerRepository.save(customer);
	        return ResponseEntity.status(200).body(new Response<Customer>("200", "success", customer));
		}
		catch(Exception e){
			return ResponseEntity.status(500).body(new Response<Customer>("500", e.getCause().toString(), null));
		}
    }
	
	
	
	@RequestMapping(value = "/addRentToCustomer", method = RequestMethod.POST)
    public ResponseEntity<Response<Customer>> addRentToCustomer(@RequestBody String body) {
		try {
			JSONObject joBody = new JSONObject(body);
			String customer_email = joBody.getString("customerEmail");
			Customer customer = customerRepository.findByEmail(customer_email);
			String rent_type_name = joBody.getString("rentTypeName");
			RentType rentType = rentTypeRepository.findByName(rent_type_name);
			
			Rent rent = new Rent(
				rentType,
				customer,
				joBody.getInt("time"),
				joBody.getString("measure")
			);
			
			customer.addRent(rent);
	        customerRepository.save(customer);
	        return ResponseEntity.status(200).body(new Response<Customer>("200", "success", customer));
		}
		catch(Exception e) {
			return ResponseEntity.status(500).body(new Response<Customer>("500", e.getCause().toString(), null));
		}
    }
	
	
	
	
	@RequestMapping(value = "/getAllRentTypes", method = RequestMethod.GET)
    public List<RentType> getAllRentTypes() {
		Iterator<RentType> iterator = rentTypeRepository.findAll().iterator();
		List<RentType> list = new ArrayList<RentType>();
		iterator.forEachRemaining( elem -> {
			list.add(elem);
		});
        return list;
    }
	
	@RequestMapping(value = "/getAllRents", method = RequestMethod.GET)
    public List<Rent> getAllRents() {
		Iterator<Rent> iterator = rentRepository.findAll().iterator();
		List<Rent> list = new ArrayList<Rent>();
		iterator.forEachRemaining( elem -> {
			list.add(elem);
		});
        return list;
    }
	
	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
		Iterator<Customer> iterator = customerRepository.findAll().iterator();
		List<Customer> list = new ArrayList<Customer>();
		iterator.forEachRemaining( elem -> {
			list.add(elem);
		});
        return list;
    }
	
	

}
