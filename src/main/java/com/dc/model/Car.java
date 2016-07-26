package com.dc.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CARS")
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CAR")
	private Long id;
	
	@Column(name = "BRAND")
	private String brand;
	
	@Column(name = "YEAR")
	private Short year;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name = "PLACES")
	private Byte places;
	
	@ManyToMany(mappedBy = "cars", fetch = FetchType.EAGER)
	private Set<Driver> drivers;
	
	public Car() {}

	public Car(String brand, Short year, String type, Byte places, Set<Driver> drivers) {
		this.brand = brand;
		this.year = year;
		this.type = type;
		this.places = places;
		this.drivers = drivers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Short getYear() {
		return year;
	}

	public void setYear(Short year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Byte getPlaces() {
		return places;
	}

	public void setPlaces(Byte places) {
		this.places = places;
	}

	public Set<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(Set<Driver> drivers) {
		this.drivers = drivers;
	}
	
}
