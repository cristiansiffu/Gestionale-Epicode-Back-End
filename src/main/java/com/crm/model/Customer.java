package com.crm.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idCustomer;

	private String companyName;

	private String vatNumber;

	private String email;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate insertDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate lastContactDay;

	private BigDecimal annualTurnover;

	private String cem;

	private String phone;

	private String emailContact;

	private String firstName;

	private String lastName;

	private String phoneContact;

	@ManyToOne
	private HeadQuarter headOffice;

	@ManyToOne(optional = true)
	private HeadQuarter registeredOffice;

	@OneToMany(mappedBy = "customer")
	private List<Invoice> invoices = new ArrayList<>();

	@Enumerated(EnumType.STRING)
	private CustomerTypes type;

}
