package com.crm.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.crm.model.Municipality;
import com.crm.service.impl.MunicipalityServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/municipality")
public class MunicipalityController {

	// Aggiunta opzionale di nuovi comuni

	@Autowired
	private MunicipalityServiceImpl municipalityServiceImpl;

	@GetMapping("/getAllMunicipalities")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Municipality>> getAllHeadQuarters(Pageable pageable) {
		Page<Municipality> municipality = municipalityServiceImpl.getM(pageable);
		return new ResponseEntity<>(municipality, HttpStatus.OK);
	}

	@GetMapping("/getMunicipalityById/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Municipality> getByIdMunicipality(@PathVariable Long id) {
		Municipality municipality = municipalityServiceImpl.getMById(id);
		return new ResponseEntity<Municipality>(municipality, HttpStatus.OK);

	}

	@PostMapping("/addMunicipality")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Municipality> addMunicipality(@RequestBody Municipality m) {
		return new ResponseEntity<>(municipalityServiceImpl.postM(m), HttpStatus.OK);
	}

	@PutMapping("/updateMunicipality/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Municipality> updateMunicipality(@PathVariable Long id, @RequestBody Municipality m) {
		return new ResponseEntity<>(municipalityServiceImpl.putM(id, m), HttpStatus.OK);
	}

	@DeleteMapping("/deleteMunicipality/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<?> deleteMunicipality(@PathVariable Long id) {
		municipalityServiceImpl.deleteM(id);
		return new ResponseEntity<String>("Municipality removed", HttpStatus.OK);
	}

}
