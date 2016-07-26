package com.dc.model;

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
import javax.persistence.Table;

@Entity
@Table(name = "DRIVERS")
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DRIVER")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USER")
	private User user;
	
	@Column(name = "EXPERIENCE")
	private Byte experience = 0;
	
	@Column(name = "RATING")
	private Float rating = (float) 0;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CAR")
	private Car car;

	public Driver() {}

	public Driver(User user, Byte experience, Float rating, Car car) {
		this.user = user;
		this.experience = experience;
		this.rating = rating;
		this.car = car;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Byte getExperience() {
		return experience;
	}

	public void setExperience(Byte experience) {
		this.experience = experience;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
}
