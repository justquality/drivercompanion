package com.dc.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class TripForm {
	private String departure;
	private String arrival;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private Double maxPrice;
	private Byte minExperience;
	private Byte maxExperience;
	
	public TripForm() {
		date = new Date();
		maxPrice = (double) 100;
		minExperience = 2;
		maxExperience = 5;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Byte getMinExperience() {
		return minExperience;
	}

	public void setMinExperience(Byte minExperience) {
		this.minExperience = minExperience;
	}

	public Byte getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(Byte maxExperience) {
		this.maxExperience = maxExperience;
	}
	
}
