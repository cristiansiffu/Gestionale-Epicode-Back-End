package com.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.Municipality;

public interface MunicipalityService {

	public Municipality getMById(Long id);

	public Page<Municipality> getM(Pageable pageable);

	public Municipality postM(Municipality municipality);

	public Municipality putM(Long id, Municipality municipality);

	public void deleteM(Long id);

}
