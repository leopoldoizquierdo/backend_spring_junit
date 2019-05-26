package com.example.demo.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Rent;

@Repository
public interface RentRepository extends CrudRepository<Rent, Long>{

}

