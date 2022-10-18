package com.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Municipality {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idMunicipality;

	private String name;

	@ManyToOne
	private Province province;

	public Municipality(String name, Province province) {
		this.name = name;
		this.province = province;
	}
}