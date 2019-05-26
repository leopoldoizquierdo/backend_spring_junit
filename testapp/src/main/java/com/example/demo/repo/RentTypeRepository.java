package com.example.demo.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Rent;
import com.example.demo.model.RentType;

@Repository
public interface RentTypeRepository extends CrudRepository<RentType, Long>{
	RentType findByName(String name);
}