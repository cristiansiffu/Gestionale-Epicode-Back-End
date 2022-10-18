package com.crm.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	public Optional<Customer> findById(Long id);

	public Page<Customer> findByAnnualTurnoverBetweenOrderByAnnualTurnover(BigDecimal annualTurnoverMin,
			BigDecimal annualTurnoverMax, Pageable pageable);

	public Page<Customer> findByInsertDateOrderByInsertDate(LocalDate insertDate, Pageable pageable);

	public Page<Customer> findByLastContactDayOrderByLastContactDay(LocalDate lastContactDay, Pageable pageable);

	public Page<Customer> findByLastName(String lastName, Pageable pageable);

}
