package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
