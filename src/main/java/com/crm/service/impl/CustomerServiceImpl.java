package com.crm.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.model.Customer;
import com.crm.repository.CustomerRepository;
import com.crm.repository.HeadQuarterRepository;
import com.crm.service.CustomerService;
import com.crm.util.exception.CRMException;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private HeadQuarterRepository headQuarterRepository;

	@Override
	public Page<Customer> getC(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	@Override
	public Customer postC(Customer customer) {
		headQuarterRepository.save(customer.getHeadOffice());
		headQuarterRepository.save(customer.getRegisteredOffice());
		return customerRepository.save(customer);
	}

	@Override
	public Customer putC(Long id, Customer customer) {
		Optional<Customer> oldCustomer = customerRepository.findById(id);
		if (oldCustomer.isEmpty()) {
			throw new CRMException("Customer not found.");
		}
		oldCustomer.get().setEmail(customer.getEmail());
		oldCustomer.get().setFirstName(customer.getFirstName());
		oldCustomer.get().setLastName(customer.getLastName());
		oldCustomer.get().setPhone(customer.getPhone());
		oldCustomer.get().setLastContactDay(customer.getLastContactDay());
		customerRepository.save(oldCustomer.get());
		return oldCustomer.get();
	}

	@Override
	public void deleteC(Long id) {
		if (customerRepository.findById(id).isEmpty()) {
			throw new CRMException("Customer not found.");
		}
		customerRepository.deleteById(id);
	}

	@Override
	public Page<Customer> filterByAnnualTurnover(BigDecimal annualTurnoverMin, BigDecimal annualTurnoverMax,
			Pageable pageable) {
		return customerRepository.findByAnnualTurnoverBetweenOrderByAnnualTurnover(annualTurnoverMin, annualTurnoverMax,
				pageable);
	}

	@Override
	public Page<Customer> filterByInsertDate(LocalDate insertDate, Pageable pageable) {
		return customerRepository.findByInsertDateOrderByInsertDate(insertDate, pageable);
	}

	@Override
	public Page<Customer> filterByLastContactDay(LocalDate lastContactDay, Pageable pageable) {
		return customerRepository.findByLastContactDayOrderByLastContactDay(lastContactDay, pageable);
	}

	@Override
	public Page<Customer> filterByLastName(String lastName, Pageable pageable) {
		return customerRepository.findByLastName(lastName, pageable);
	}

	@Override
	public Customer getCById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isEmpty()) {
			throw new CRMException("Customer not found.");
		}
		return customer.get();
	}

}
