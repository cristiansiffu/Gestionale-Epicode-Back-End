package com.crm.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crm.model.Customer;
import com.crm.model.Invoice;
import com.crm.model.InvoiceStatus;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

	public Optional<Invoice> findById(Long id);

	public Page<Invoice> findByCustomer(Customer customer, Pageable pageable);

	public Page<Invoice> findByStatus(InvoiceStatus status, Pageable pageable);

	public Page<Invoice> findByInsertDate(LocalDate insertDate, Pageable pageable);

	public Page<Invoice> findByYear(int year, Pageable pageable);

	public Page<Invoice> findByAmountBetween(BigDecimal annualTurnoverMin, BigDecimal annualTurnoverMax,
			Pageable pageable);

}
