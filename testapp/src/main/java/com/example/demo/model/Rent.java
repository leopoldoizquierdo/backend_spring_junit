package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rent")
public class Rent {
	
	@Id
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "sequence-generator"
	)
	@SequenceGenerator(
		name = "sequence-generator", 
		sequenceName = "rent_sequence",
		allocationSize = 1
	)
	@Column(name = "id")
	private long id;
	

	
	@Column(name = "time")
	@Min(value = 0L, message = "The value must be positive")
	private int time;
	
	
	@Column(name = "measure", length = 256)
	@NotEmpty
	private String measure;
	
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rent_type_id")
	@NotNull
	private RentType rentType;
	

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer.id")
	@NotNull
	private Customer customer;

	
	
	
	public Rent(RentType rentType, Customer customer, int time, String measure) {
		super();
		this.rentType = rentType;
		this.customer = customer;
		this.time = time;
		this.measure = measure;
	}
	
	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public Rent() {
		super();
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public RentType getRentType() {
		return rentType;
	}




	public void setRentType(RentType rentType) {
		this.rentType = rentType;
	}




	public Customer getCustomer() {
		return customer;
	}




	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Override
	public String toString() {
		return "{"
				+ "\"id\":\"" + id + "\","
				+ "\"rentType\":\"" + rentType.toString() + "\","
				+ "\"customer\":\"" + customer.getEmail() + "\","
				+ "\"time\":\"" + time + "\","
				+ "\"measure\":\"" + measure + "\""
				+ "}";
	}

}




