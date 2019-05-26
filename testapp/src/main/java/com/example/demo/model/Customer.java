package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "customer")
public class Customer {

	@Id
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "sequence-generator"
	)
	@SequenceGenerator(
		name = "sequence-generator", 
		sequenceName = "customer_sequence",
		allocationSize = 1
	)
	@Column(name = "id")
	private long id;
	
	@Column(name = "email", length = 1024, unique = true)
	private String email;
	
	
	
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rent> listRents;

    
    
    

	public Customer(String email) {
		super();
		this.email = email;
		this.listRents = new ArrayList<Rent>();
	}
	
	public Customer() {
		super();
	}
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<Rent> getListRents() {
		return listRents;
	}


	public void setListRents(List<Rent> listRents) {
		this.listRents = listRents;
	}
	
	public void addRent(Rent rent) {
		this.listRents.add(rent);
	}
    
  
	
	@Override
	public String toString() {
		return "{"
				+ "\"id\":\"" + id + "\","
				+ "\"email\":\"" + email + "\","
				+ "\"listRents\":\"" + listRents.toString() + "\""
				+ "}";
	}
	
	
}
