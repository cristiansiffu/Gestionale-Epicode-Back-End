package com.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.service.impl.CSVReader;

@RestController
@RequestMapping("/csv")
public class CSVController {

	@Autowired
	private CSVReader csvReader;

	@PostMapping("/register")
	public ResponseEntity<?> register() {
		return csvReader.readMunicipality();
	}
}
