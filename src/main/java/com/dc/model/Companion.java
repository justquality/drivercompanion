package com.dc.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COMPANIONS")
public class Companion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_COMPANION")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USER")
	private User user;
	
	@Column(name = "RATING")
	private Float rating = (float) 0;
	
	@ManyToMany(mappedBy = "companions", fetch = FetchType.EAGER)
	private Set<Trip> trips;
	
	@OneToMany(mappedBy = "companionAuthor")
	private Set<Review> authorReviews;
	
	@OneToMany(mappedBy = "companionTarget")
	private Set<Review> targetReviews;

	public Companion() {}

	public Companion(User user, Float rating, Set<Trip> trips) {
		this.user = user;
		this.rating = rating;
		this.trips = trips;
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

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
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
