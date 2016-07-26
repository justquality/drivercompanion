package com.dc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "REVIEWS")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_REVIEW")
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_AUTHOR")
	private User userAuthor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_TARGET")
	private User userTarget;
	
	@Column(name = "AUTHOR_NAME")
	private String authorName;
	
	@Column(name = "MARK")
	private Byte mark;
	
	@Column(name = "COMMENT")
	private String comment;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "REVIEW_DATE")
	private Date date;
	
	public Review() {}

	public Review(User userAuthor, User userTarget, String authorName, Byte mark, String comment, Date date) {
		this.userAuthor = userAuthor;
		this.userTarget = userTarget;
		this.authorName = authorName;
		this.mark = mark;
		this.comment = comment;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUserAuthor() {
		return userAuthor;
	}

	public void setUserAuthor(User userAuthor) {
		this.userAuthor = userAuthor;
	}

	public User getUserTarget() {
		return userTarget;
	}

	public void setUserTarget(User userTarget) {
		this.userTarget = userTarget;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
