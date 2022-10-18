package com.crm.model.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.crm.model.HeadQuarter;
import com.crm.model.dto.HeadQuarterDTO;
import com.crm.repository.MunicipalityRepository;

@Component
public class HeadQuarterDTOConverter implements Converter<HeadQuarterDTO, HeadQuarter> {

	@Autowired
	private MunicipalityRepository municipalityRepository;

	@Override
	public HeadQuarter convert(HeadQuarterDTO source) {
		HeadQuarter headQuarter = new HeadQuarter();
		headQuarter.setIdHQ(source.getIdHQ());
		headQuarter.setLocation(source.getLocation());
		headQuarter.setMunicipality(municipalityRepository.findById(source.getIdM()).get());
		headQuarter.setPostcode(source.getPostcode());
		headQuarter.setStreetNumber(source.getStreetNumber());
		headQuarter.setVia(source.getVia());
		return headQuarter;
	}

}
