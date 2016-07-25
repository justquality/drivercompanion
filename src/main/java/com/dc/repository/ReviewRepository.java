package com.dc.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Review;
import com.dc.model.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	Set<Review> findByUserAuthor(User user);
	Set<Review> findByUserTarget(User user);
}
