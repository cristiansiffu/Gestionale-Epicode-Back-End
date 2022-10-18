package com.crm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.model.Province;
import com.crm.repository.ProvinceRepository;
import com.crm.service.ProvinceService;
import com.crm.util.exception.CRMException;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	@Override
	public Page<Province> getP(Pageable pageable) {
		return provinceRepository.findAll(pageable);
	}

	@Override
	public Province postP(Province province) {
		return provinceRepository.save(province);
	}

	@Override
	public Province putP(Long id, Province province) {
		Optional<Province> oldProvince = provinceRepository.findById(id);
		if (!provinceRepository.existsById(id)) {
			throw new CRMException("Municipality not found.");
		}
		Province newProvince = oldProvince.get();
		newProvince.setInitial(province.getInitial());
		newProvince.setName(province.getName());
		return newProvince;
	}

	@Override
	public void deleteP(Long id) {
		if (!provinceRepository.existsById(id)) {
			throw new CRMException("Province not found.");
		}
		provinceRepository.deleteById(id);
	}

	@Override
	public Province getPById(Long id) {
		Optional<Province> province = provinceRepository.findById(id);
		if (!provinceRepository.existsById(id)) {
			throw new CRMException("Province not found.");
		}
		return province.get();
	}

}
