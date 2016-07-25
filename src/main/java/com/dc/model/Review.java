package com.dc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "REVIEWS")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_REVIEW")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_AUTHOR")
	private Driver driverAuthor;
	
	@ManyToOne
	@JoinColumn(name = "ID_TARGET")
	private Driver driverTarget;
	
	@ManyToOne
	@JoinColumn(name = "ID_AUTHOR")
	private Companion companionAuthor;
	
	@ManyToOne
	@JoinColumn(name = "ID_TARGET")
	private Companion companionTarget;
	
	@Column(name = "AUTHOR_NAME")
	private String authorName;
	
	@Column(name = "MARK")
	private Byte mark;
	
	@Column(name = "COMMENT")
	private String comment;
	
	public Review() {}

	public Review(Driver driverAuthor, Driver driverTarget, Companion companionAuthor, Companion companionTarget,
			String authorName, Byte mark, String comment) {
		this.driverAuthor = driverAuthor;
		this.driverTarget = driverTarget;
		this.companionAuthor = companionAuthor;
		this.companionTarget = companionTarget;
		this.authorName = authorName;
		this.mark = mark;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getDriverAuthor() {
		return driverAuthor;
	}

	public void setDriverAuthor(Driver driverAuthor) {
		this.driverAuthor = driverAuthor;
	}

	public Driver getDriverTarget() {
		return driverTarget;
	}

	public void setDriverTarget(Driver driverTarget) {
		this.driverTarget = driverTarget;
	}

	public Companion getCompanionAuthor() {
		return companionAuthor;
	}

	public void setCompanionAuthor(Companion companionAuthor) {
		this.companionAuthor = companionAuthor;
	}

	public Companion getCompanionTarget() {
		return companionTarget;
	}

	public void setCompanionTarget(Companion companionTarget) {
		this.companionTarget = companionTarget;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Byte getMark() {
		return mark;
	}

	public void setMark(Byte mark) {
		this.mark = mark;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
