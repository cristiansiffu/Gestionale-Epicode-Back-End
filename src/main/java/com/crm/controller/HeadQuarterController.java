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

import com.crm.model.HeadQuarter;
import com.crm.model.dto.HeadQuarterDTO;
import com.crm.model.dto.converter.HeadQuarterDTOConverter;
import com.crm.service.impl.HeadQuarterServiceImpl;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/headQuarter")
public class HeadQuarterController {
	
	@Autowired
	private HeadQuarterDTOConverter headQuarterDTOConverter;

	@Autowired
	private HeadQuarterServiceImpl headQuarterServiceImpl;

	@GetMapping("/getAllHeadQuarters")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<Page<HeadQuarter>> getAllHeadQuarters(Pageable pageable) {
		Page<HeadQuarter> headQuarters = headQuarterServiceImpl.getHQ(pageable);
		return new ResponseEntity<>(headQuarters, HttpStatus.OK);
	}

	@GetMapping("/getHeadQuarterById/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<HeadQuarter> getByIdHeadQuarter(@PathVariable Long id) {
		HeadQuarter headQuarter = headQuarterServiceImpl.getHQById(id);
		return new ResponseEntity<HeadQuarter>(headQuarter, HttpStatus.OK);

	}

	@PostMapping("/addHeadQuarter")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<HeadQuarter> addHeadQuarter(@RequestBody HeadQuarterDTO headQuarterDTO) {
		HeadQuarter headQuarter = headQuarterDTOConverter.convert(headQuarterDTO);
		return new ResponseEntity<>(headQuarterServiceImpl.postHQ(headQuarter), HttpStatus.OK);
	}

	@PutMapping("/updateHeadQuarter/{id}")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<HeadQuarter> updateHeadQuarter(@PathVariable Long id,
			@RequestBody HeadQuarterDTO headQuarterDTO) {
		HeadQuarter headQuarter = headQuarterDTOConverter.convert(headQuarterDTO);
		return new ResponseEntity<>(headQuarterServiceImpl.putHQ(id, headQuarter), HttpStatus.OK);
	}

	@DeleteMapping("/deleteHeadQuarter/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@SecurityRequirement(name = "bearerAuth")
	public ResponseEntity<?> deleteHeadQuarter(@PathVariable Long id) {
		headQuarterServiceImpl.deleteHQ(id);
		return new ResponseEntity<String>("Head-Quarter removed", HttpStatus.OK);
	}
}
