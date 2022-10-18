package com.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class HeadQuarter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idHQ;

	private String via;

	private String streetNumber;

	private String location;

	private String postcode;

	@ManyToOne
	private Municipality municipality;

}
