package com.crm.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idInvoice;

	private int year;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate insertDate;

	private BigDecimal amount;

	private int number;

	@Enumerated(EnumType.STRING)
	private InvoiceStatus status;

	@JoinColumn
	@ManyToOne
	private Customer customer;

}
