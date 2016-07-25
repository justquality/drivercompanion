package com.dc.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.model.Review;
import com.dc.model.User;
import com.dc.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Override
	public void save(Review review) {
		reviewRepository.save(review);
	}

	@Override
	public Set<Review> findByUserAuthor(User user) {
		return reviewRepository.findByUserAuthor(user);
	}

	@Override
	public Set<Review> findByUserTarget(User user) {
		return reviewRepository.findByUserTarget(user);
	}

}
