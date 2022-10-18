package com.crm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.model.Municipality;
import com.crm.repository.MunicipalityRepository;
import com.crm.service.MunicipalityService;
import com.crm.util.exception.CRMException;

@Service
public class MunicipalityServiceImpl implements MunicipalityService {

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Override
	public Page<Municipality> getM(Pageable pageable) {
		return municipalityRepository.findAll(pageable);
	}

	@Override
	public Municipality postM(Municipality municipality) {
		return municipalityRepository.save(municipality);
	}

	@Override
	public Municipality putM(Long id, Municipality municipality) {
		Optional<Municipality> oldMunicipality = municipalityRepository.findById(id);
		if (!municipalityRepository.existsById(id)) {
			throw new CRMException("Municipality not found.");
		}
		Municipality newMunicipality = oldMunicipality.get();
		newMunicipality.setName(municipality.getName());
		return newMunicipality;
	}

	@Override
	public void deleteM(Long id) {
		if (!municipalityRepository.existsById(id)) {
			throw new CRMException("Municipality not found.");
		}
		municipalityRepository.deleteById(id);
	}

	@Override
	public Municipality getMById(Long id) {
		Optional<Municipality> municipality = municipalityRepository.findById(id);
		if (!municipalityRepository.existsById(id)) {
			throw new CRMException("Municipality not found.");
		}
		return municipality.get();
	}

}
