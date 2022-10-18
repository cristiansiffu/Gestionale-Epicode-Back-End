package com.crm.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeadQuarterDTO {

	private Long idHQ;

	private String via;

	private String streetNumber;

	private String location;

	private String postcode;

	private Long idM;
}
