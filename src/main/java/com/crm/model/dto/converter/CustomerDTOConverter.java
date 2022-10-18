package com.crm.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.crm.model.Customer;
import com.crm.model.dto.CustomerDTO;

@Component
public class CustomerDTOConverter implements Converter<CustomerDTO, Customer> {

	@Autowired
	private HeadQuarterDTOConverter headQuarterC;

	@Override
	public Customer convert(CustomerDTO source) {
		Customer customer = new Customer();
		customer.setIdCustomer(source.getIdCustomer());
		customer.setAnnualTurnover(source.getAnnualTurnover());
		customer.setCem(source.getCem());
		customer.setCompanyName(source.getCompanyName());
		customer.setEmail(source.getEmail());
		customer.setEmailContact(source.getEmailContact());
		customer.setFirstName(source.getFirstName());
		customer.setHeadOffice(headQuarterC.convert(source.getHeadOffice()));
		customer.setInsertDate(source.getInsertDate());
		customer.setLastContactDay(source.getLastContactDay());
		customer.setLastName(source.getLastName());
		customer.setPhone(source.getPhone());
		customer.setPhoneContact(source.getPhoneContact());
		customer.setRegisteredOffice(headQuarterC.convert(source.getRegisteredOffice()));
		customer.setType(source.getType());
		customer.setVatNumber(source.getVatNumber());
		return customer;
	}

}
