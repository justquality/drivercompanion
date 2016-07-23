package com.dc.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRIPS")
public class Trip {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TRIP")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_DRIVER")
	private Driver driver;
	
	@Column(name = "DEPARTURE")
	private String departure;
	
	@Column(name = "ARRIVAL")
	private String arrival;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "TRIP_DATE")
	private Date date;
	
	@ManyToMany
	@JoinTable(name = "TRIPS_COMPANIONS", joinColumns = @JoinColumn(name = "ID_TRIP"),
			inverseJoinColumns = @JoinColumn(name = "ID_COMPANION"))
	private Set<Companion> companions;
	
	public Trip() {}

	public Trip(Driver driver, String departure, String arrival, Double price, Date date, Set<Companion> companions) {
		this.driver = driver;
		this.departure = departure;
		this.arrival = arrival;
		this.price = price;
		this.date = date;
		this.companions = companions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrival() {
		return arrival;
	}

	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<Companion> getCompanions() {
		return companions;
	}

	public void setCompanions(Set<Companion> companions) {
		this.companions = companions;
	}
	
}
