package com.dc.service;

import java.util.Set;

import com.dc.model.Review;
import com.dc.model.User;

public interface ReviewService {
	void save (Review review);
	Set<Review> findByUserAuthor(User user);
	Set<Review> findByUserTarget(User user);
}
