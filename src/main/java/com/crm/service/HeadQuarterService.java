package com.crm.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.crm.model.HeadQuarter;

public interface HeadQuarterService {

	public HeadQuarter getHQById(Long id);

	public Page<HeadQuarter> getHQ(Pageable pageable);

	public HeadQuarter postHQ(HeadQuarter headQuarter);

	public HeadQuarter putHQ(Long id, HeadQuarter headQuarter);

	public void deleteHQ(Long id);

}
