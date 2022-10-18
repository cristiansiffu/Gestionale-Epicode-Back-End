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

import com.crm.model.Province;
import com.crm.service.impl.ProvinceServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/province")
public class ProvinceController {

	// Aggiunta opzionale di nuove province

	@Autowired
	private ProvinceServiceImpl provinceServiceImpl;

	@GetMapping("/getAllProvinces")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<Province>> getAllProvinces(Pageable pageable) {
		Page<Province> municipality = provinceServiceImpl.getP(pageable);
		return new ResponseEntity<>(municipality, HttpStatus.OK);
	}

	@GetMapping("/getProvinceById/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Province> getByIdMunicipality(@PathVariable Long id) {
		Province municipality = provinceServiceImpl.getPById(id);
		return new ResponseEntity<Province>(municipality, HttpStatus.OK);

	}

	@PostMapping("/addProvince")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Province> addProvince(@RequestBody Province p) {
		return new ResponseEntity<>(provinceServiceImpl.postP(p), HttpStatus.OK);
	}

	@PutMapping("/updateProvince/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Province> updateProvince(@PathVariable Long id, @RequestBody Province p) {
		return new ResponseEntity<>(provinceServiceImpl.putP(id, p), HttpStatus.OK);
	}

	@DeleteMapping("/deleteProvince/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<?> deleteProvince(@PathVariable Long id) {
		provinceServiceImpl.deleteP(id);
		return new ResponseEntity<String>("Province removed", HttpStatus.OK);
	}

}
