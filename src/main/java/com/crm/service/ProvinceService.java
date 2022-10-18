package com.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Province;

public interface ProvinceService {

	public Province getPById(Long id);

	public Page<Province> getP(Pageable pageable);

	public Province postP(Province province);

	public Province putP(Long id, Province province);

	public void deleteP(Long id);

}
