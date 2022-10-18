package com.crm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Customer;

public interface CustomerService {

	public Customer getCById(Long id);

	public Page<Customer> getC(Pageable pageable);

	public Customer postC(Customer customer);

	public Customer putC(Long id, Customer customer);

	public void deleteC(Long id);

	public Page<Customer> filterByAnnualTurnover(BigDecimal annualTurnoverMin, BigDecimal annualTurnoverMax,
			Pageable pageable);

	public Page<Customer> filterByInsertDate(LocalDate insertDate, Pageable pageable);

	public Page<Customer> filterByLastContactDay(LocalDate lastContactDay, Pageable pageable);

	public Page<Customer> filterByLastName(String lastName, Pageable pageable);

}
