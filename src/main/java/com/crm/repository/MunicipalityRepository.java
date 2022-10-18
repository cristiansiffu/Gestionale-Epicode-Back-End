package com.crm.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.Municipality;

@Repository
public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
	
	public Optional<Municipality> findById(Long id);

	public Page<Municipality> findByName(String name, Pageable pageable);

}
