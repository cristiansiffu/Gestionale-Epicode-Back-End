package com.crm.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Customer;
import com.crm.model.Invoice;
import com.crm.model.InvoiceStatus;

public interface InvoiceService {

	public Invoice getIById(Long id);

	public Page<Invoice> getI(Pageable pageable);

	public Invoice postI(Invoice invoice);

	public Invoice putI(Long id, Invoice invoice);

	public void deleteI(Long id);

	// Filter

	public Page<Invoice> filterByCustomer(Long idC, Pageable pageable);

	public Page<Invoice> filterByInvoiceStatus(InvoiceStatus invoiceStatus, Pageable pageable);

	public Page<Invoice> filterByInsertDate(LocalDate insertDate, Pageable pageable);

	public Page<Invoice> filterByYear(int year, Pageable pageable);

	public Page<Invoice> filterByAmount(BigDecimal amountMin, BigDecimal amountMax,
			Pageable pageable);

}
