package com.crm.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Customer;
import com.crm.model.dto.CustomerDTO;
import com.crm.model.dto.converter.CustomerDTOConverter;
import com.crm.service.impl.CustomerServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerDTOConverter customerDTOConverter;

	@Autowired
	private CustomerServiceImpl customerServiceImpl;

	@GetMapping("/getAllCustomers")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Customer>> getAllCustomers(Pageable pageable) {
		Page<Customer> customers = customerServiceImpl.getC(pageable);
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}

	@GetMapping("/getCustomerById/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Customer> getByIdCustomer(@PathVariable Long id) {
		Customer customer = customerServiceImpl.getCById(id);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@PostMapping("/addCustomer")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Customer> addCustomer(@RequestBody CustomerDTO customerDTO) {
		Customer customer = customerDTOConverter.convert(customerDTO);
		return new ResponseEntity<>(customerServiceImpl.postC(customer), HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		Customer customer = customerDTOConverter.convert(customerDTO);
		return new ResponseEntity<>(customerServiceImpl.putC(id, customer), HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
		customerServiceImpl.deleteC(id);
		return new ResponseEntity<String>("Customer removed", HttpStatus.OK);
	}

	@GetMapping("/getByAnnualTurnover")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Customer>> findByAnnualTurnover(@RequestParam BigDecimal annualTurnoverMin,
			@RequestParam BigDecimal annualTurnoverMax, Pageable pageable) {
		Page<Customer> customers = customerServiceImpl.filterByAnnualTurnover(annualTurnoverMin, annualTurnoverMax,
				pageable);
		return new ResponseEntity<Page<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("/getByInsertDate")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Customer>> findByInsertDate(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate insertDate, Pageable pageable) {
		Page<Customer> customers = customerServiceImpl.filterByInsertDate(insertDate, pageable);
		return new ResponseEntity<Page<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("/getByLastContactDay")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Customer>> findByLastContactDay(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate lastContactDay, Pageable pageable) {
		Page<Customer> customers = customerServiceImpl.filterByLastContactDay(lastContactDay, pageable);
		return new ResponseEntity<Page<Customer>>(customers, HttpStatus.OK);
	}

	@GetMapping("/getByLastName")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Customer>> findByLastContactDay(@RequestParam String lastName, Pageable pageable) {
		Page<Customer> customers = customerServiceImpl.filterByLastName(lastName, pageable);
		return new ResponseEntity<Page<Customer>>(customers, HttpStatus.OK);
	}

}
