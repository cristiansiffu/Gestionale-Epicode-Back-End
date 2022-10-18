package com.crm.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

	public Optional<Province> findById(Long id);

	public Page<Province> findByName(String name, Pageable pageable);

}
