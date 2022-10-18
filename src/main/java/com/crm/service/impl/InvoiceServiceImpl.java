package com.crm.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.model.Invoice;
import com.crm.model.InvoiceStatus;
import com.crm.repository.CustomerRepository;
import com.crm.repository.InvoiceRepository;
import com.crm.service.InvoiceService;
import com.crm.util.exception.CRMException;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Page<Invoice> getI(Pageable pageable) {
		return invoiceRepository.findAll(pageable);
	}

	@Override
	public Invoice postI(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@Override
	public Invoice putI(Long id, Invoice invoice) {
		Optional<Invoice> oldInvoice = invoiceRepository.findById(id);
		if (oldInvoice.isEmpty()) {
			throw new CRMException("Invoice not found.");
		}
		oldInvoice.get().setStatus(invoice.getStatus());
		invoiceRepository.save(oldInvoice.get());
		return oldInvoice.get();
	}

	@Override
	public void deleteI(Long id) {
		if (invoiceRepository.findById(id).isEmpty()) {
			throw new CRMException("Invoice not found.");
		}
		invoiceRepository.deleteById(id);
	}

	@Override
	public Page<Invoice> filterByCustomer(Long idC, Pageable pageable) {
		Page<Invoice> invoice = invoiceRepository.findByCustomer(customerRepository.findById(idC).get(), pageable);
		// Per semplificare la paginazione evitando l'annidamento in loop tra fatture e
		// cliente durante le prove del metodo
		for (Invoice i : invoice) {
			i.getCustomer().setInvoices(null);
		}
		return invoice;
	}

	@Override
	public Page<Invoice> filterByInvoiceStatus(InvoiceStatus invoiceStatus, Pageable pageable) {
		return invoiceRepository.findByStatus(invoiceStatus, pageable);
	}

	@Override
	public Page<Invoice> filterByInsertDate(LocalDate insertDate, Pageable pageable) {
		return invoiceRepository.findByInsertDate(insertDate, pageable);
	}

	@Override
	public Page<Invoice> filterByYear(int year, Pageable pageable) {
		return invoiceRepository.findByYear(year, pageable);
	}

	@Override
	public Page<Invoice> filterByAmount(BigDecimal amountMin, BigDecimal amountMax,
			Pageable pageable) {
		return invoiceRepository.findByAmountBetween(amountMin, amountMax, pageable);
	}

	@Override
	public Invoice getIById(Long id) {
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		if (invoiceRepository.findById(id).isEmpty()) {
			throw new CRMException("Invoice not found.");
		}
		return invoice.get();
	}

}
