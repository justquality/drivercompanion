package com.dc.model;

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
import javax.persistence.OneToMany;
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
	
	@ManyToMany
	@JoinTable(name = "DRIVERS_CARS", joinColumns = @JoinColumn(name = "ID_DRIVER"),
			inverseJoinColumns = @JoinColumn(name = "ID_CAR"))
	private Set<Car> cars;
	
	@OneToMany(mappedBy = "driverAuthor")
	private Set<Review> authorReviews;
	
	@OneToMany(mappedBy = "driverTarget")
	private Set<Review> targetReviews;

	public Driver() {}

	public Driver(User user, Byte experience, Float rating, Set<Car> cars) {
		this.user = user;
		this.experience = experience;
		this.rating = rating;
		this.cars = cars;
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

	public Set<Car> getCars() {
		return cars;
	}

	public void setCars(Set<Car> cars) {
		this.cars = cars;
	}

	public Set<Review> getAuthorReviews() {
		return authorReviews;
	}

	public void setAuthorReviews(Set<Review> authorReviews) {
		this.authorReviews = authorReviews;
	}

	public Set<Review> getTargetReviews() {
		return targetReviews;
	}

	public void setTargetReviews(Set<Review> targetReviews) {
		this.targetReviews = targetReviews;
	}
	
}
