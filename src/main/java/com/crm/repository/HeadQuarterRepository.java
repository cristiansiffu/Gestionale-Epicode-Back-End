package com.crm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.HeadQuarter;

@Repository
public interface HeadQuarterRepository extends JpaRepository<HeadQuarter, Long> {
	
	public Optional<HeadQuarter> findById(Long id);

}
