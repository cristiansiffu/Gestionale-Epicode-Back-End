package com.crm.controller;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.crm.model.Invoice;
import com.crm.model.InvoiceStatus;
import com.crm.model.dto.InvoiceDTO;
import com.crm.model.dto.converter.InvoiceDTOConverter;
import com.crm.service.impl.InvoiceServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {
	@Autowired
	private InvoiceDTOConverter invoiceDTOConverter;

	@Autowired
	private InvoiceServiceImpl invoiceServiceImpl;

	@GetMapping("/getAllInvoices")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> getAllInvoices(Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.getI(pageable);
		return new ResponseEntity<>(invoices, HttpStatus.OK);
	}

	@GetMapping("/getInvoiceById/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Invoice> getByIdInvoice(@PathVariable Long id) {
		Invoice invoice = invoiceServiceImpl.getIById(id);
		return new ResponseEntity<Invoice>(invoice, HttpStatus.OK);

	}

	@PostMapping("/addInvoice")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Invoice> addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDTOConverter.convert(invoiceDTO);
		return new ResponseEntity<>(invoiceServiceImpl.postI(invoice), HttpStatus.OK);
	}

	@PutMapping("/updateInvoice/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody InvoiceDTO invoiceDTO) {
		Invoice invoice = invoiceDTOConverter.convert(invoiceDTO);
		return new ResponseEntity<>(invoiceServiceImpl.putI(id, invoice), HttpStatus.OK);
	}

	@DeleteMapping("/deleteInvoice/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
		invoiceServiceImpl.deleteI(id);
		return new ResponseEntity<String>("Invoice removed", HttpStatus.OK);
	}

	@GetMapping("/getByCustomer/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> findByCustomer(@PathVariable Long id, Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.filterByCustomer(id, pageable);
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

	@GetMapping("/getByStatus/{invoiceStatus}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> findByInvoiceStatus(@PathVariable InvoiceStatus invoiceStatus,
			Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.filterByInvoiceStatus(invoiceStatus, pageable);
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

	@GetMapping("/getByInsertDate/{insertDate}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> findByInsertDate(@PathVariable LocalDate insertDate, Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.filterByInsertDate(insertDate, pageable);
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

	@GetMapping("/getByYear/{year}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> findByYear(@PathVariable int year, Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.filterByYear(year, pageable);
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

	@GetMapping("/getByAmount")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Invoice>> findByAmount(@RequestParam BigDecimal amountMin,
			@RequestParam BigDecimal amountMax, Pageable pageable) {
		Page<Invoice> invoices = invoiceServiceImpl.filterByAmount(amountMin, amountMax, pageable);
		return new ResponseEntity<Page<Invoice>>(invoices, HttpStatus.OK);
	}

}