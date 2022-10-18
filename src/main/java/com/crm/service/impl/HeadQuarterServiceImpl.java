package com.crm.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.crm.model.HeadQuarter;
import com.crm.repository.HeadQuarterRepository;
import com.crm.service.HeadQuarterService;
import com.crm.util.exception.CRMException;

@Service
public class HeadQuarterServiceImpl implements HeadQuarterService {

	@Autowired
	private HeadQuarterRepository headQuarterRepository;

	@Override
	public Page<HeadQuarter> getHQ(Pageable pageable) {
		return headQuarterRepository.findAll(pageable);
	}

	@Override
	public HeadQuarter postHQ(HeadQuarter headQuarter) {
		return headQuarterRepository.save(headQuarter);
	}

	@Override
	public HeadQuarter putHQ(Long id, HeadQuarter headQuarter) {
		Optional<HeadQuarter> oldHeadQuarter = headQuarterRepository.findById(id);
		if (oldHeadQuarter.isEmpty()) {
			throw new CRMException("Head-Quarter not found.");
		}
		oldHeadQuarter.get().setLocation(headQuarter.getLocation());
		oldHeadQuarter.get().setMunicipality(headQuarter.getMunicipality());
		oldHeadQuarter.get().setPostcode(headQuarter.getPostcode());
		oldHeadQuarter.get().setStreetNumber(headQuarter.getStreetNumber());
		oldHeadQuarter.get().setVia(headQuarter.getVia());
		headQuarterRepository.save(oldHeadQuarter.get());
		return oldHeadQuarter.get();
	}

	@Override
	public void deleteHQ(Long id) {
		if (headQuarterRepository.findById(id).isEmpty()) {
			throw new CRMException("Head-Quarter not found.");
		}
		headQuarterRepository.deleteById(id);
	}

	@Override
	public HeadQuarter getHQById(Long id) {
		Optional<HeadQuarter> headQuarter = headQuarterRepository.findById(id);
		if (headQuarterRepository.findById(id).isEmpty()) {
			throw new CRMException("Head-Quarter not found.");
		}
		return headQuarter.get();
	}

}
