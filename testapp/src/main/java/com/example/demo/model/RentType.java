package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
@Table(name = "rent_type")
public class RentType {
	
	@Id
    @GeneratedValue(
		strategy = GenerationType.SEQUENCE, 
		generator = "sequence-generator"
	)
	@SequenceGenerator(
		name = "sequence-generator", 
		sequenceName = "rent_type_sequence",
		allocationSize = 1
	)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", length = 256, unique = true)
	@NotEmpty
	private String name;
	
	@Column(name = "type_product", length = 256)
	@NotEmpty
	private String typeProduct;
	
	@Column(name = "charge_per_hour")
	@Min(value = 0L, message = "The value must be positive")
	private double chargePerHour;
	
	@Column(name = "charge_per_day")
	@Min(value = 0L, message = "The value must be positive")
	private double chargePerDay;
	
	@Column(name = "charge_per_week")
	@Min(value = 0L, message = "The value must be positive")
	private double chargePerWeek;

	

	public RentType(String name, String typeProduct, double chargePerHour, double chargePerDay, double chargePerWeek) {
		super();
		this.name = name;
		this.typeProduct = typeProduct;
		this.chargePerHour = chargePerHour;
		this.chargePerDay = chargePerDay;
		this.chargePerWeek = chargePerWeek;
	}
	
	public RentType() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeProduct() {
		return typeProduct;
	}

	public void setTypeProduct(String typeProduct) {
		this.typeProduct = typeProduct;
	}

	public double getChargePerHour() {
		return chargePerHour;
	}

	public void setChargePerHour(double chargePerHour) {
		this.chargePerHour = chargePerHour;
	}

	public double getChargePerDay() {
		return chargePerDay;
	}

	public void setChargePerDay(double chargePerDay) {
		this.chargePerDay = chargePerDay;
	}

	public double getChargePerWeek() {
		return chargePerWeek;
	}

	public void setChargePerWeek(double chargePerWeek) {
		this.chargePerWeek = chargePerWeek;
	}

	@Override
	public String toString() {
		return "{"
				+ "\"id\":\"" + id + "\","
				+ "\"name\":\"" + name + "\","
				+ "\"typeProduct\":\"" + typeProduct + "\","
				+ "\"chargePerHour\":\"" + chargePerHour + "\","
				+ "\"chargePerDay\":\"" + chargePerDay + "\","
				+ "\"chargePerWeek\":\"" + chargePerWeek + "\""
				+ "}";
	}
	
	
	
	
}
