package com.dc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dc.model.Companion;

public interface CompanionRepository extends JpaRepository<Companion, Long> {

}
