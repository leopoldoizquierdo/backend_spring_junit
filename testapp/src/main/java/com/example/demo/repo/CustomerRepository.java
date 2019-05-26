package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Customer;
import com.example.demo.model.RentType;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	Customer findByEmail(String email);
}


