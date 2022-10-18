package com.crm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idProvince;

	private String name;

	private String initial;
	
	public Province(String string, String string2) {
		name = string2;
		initial = string;
	}

}
