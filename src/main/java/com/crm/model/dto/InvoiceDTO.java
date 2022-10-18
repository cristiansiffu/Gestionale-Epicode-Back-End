package com.crm.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.crm.model.InvoiceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDTO {

	private Long idInvoice;

	private int year;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate insertDate;

	private BigDecimal amount;

	private int number;

	private InvoiceStatus status;

	private Long idC;

}
