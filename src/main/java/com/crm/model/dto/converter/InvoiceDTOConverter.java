package com.crm.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.crm.model.Invoice;
import com.crm.model.dto.InvoiceDTO;
import com.crm.repository.CustomerRepository;

@Component
public class InvoiceDTOConverter implements Converter<InvoiceDTO, Invoice> {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Invoice convert(InvoiceDTO source) {
		Invoice invoice = new Invoice();
		invoice.setIdInvoice(source.getIdInvoice());
		invoice.setAmount(source.getAmount());
		invoice.setCustomer(customerRepository.findById(source.getIdC()).get());
		invoice.setInsertDate(source.getInsertDate());
		invoice.setNumber(source.getNumber());
		invoice.setStatus(source.getStatus());
		invoice.setYear(source.getYear());
		return invoice;
	}

}
