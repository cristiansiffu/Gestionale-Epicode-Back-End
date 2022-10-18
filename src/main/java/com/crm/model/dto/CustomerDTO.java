package com.crm.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.crm.model.CustomerTypes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

	private Long idCustomer;

	@NotNull
	private String companyName;

	@NotNull
	private String vatNumber;

	@NotNull
	private String email;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate insertDate;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastContactDay;

	@NotNull
	private BigDecimal annualTurnover;

	@NotNull
	private String cem;

	@NotNull
	private String phone;

	@NotNull
	private String emailContact;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
	private String phoneContact;

	@NotNull
	private HeadQuarterDTO headOffice;

	@NotNull
	private HeadQuarterDTO registeredOffice;

	private List<InvoiceDTO> invoices = new ArrayList<>();

	@NotNull
	private CustomerTypes type;
}
