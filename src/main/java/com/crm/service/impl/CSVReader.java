package com.crm.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crm.model.Municipality;
import com.crm.model.Province;
import com.crm.repository.MunicipalityRepository;
import com.crm.repository.ProvinceRepository;

@Service
public class CSVReader {

	@Autowired
	private ProvinceRepository provinceRepository;

	@Autowired
	private MunicipalityRepository municipalityRepository;

	public ResponseEntity<?> readMunicipality() {
		provinceRepository.deleteAll();
		Map<Object, Province> provinces = readProvince();
		try (BufferedReader br = new BufferedReader(
				new FileReader("src\\main\\resources\\static\\comuni-italiani.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] municipality = line.split(";");
				if (municipality.length > 0 && (!municipality[0].contains("Codice"))) {
					Province province = provinces.get(municipality[3]);
					Municipality m = new Municipality(municipality[2], province);
					provinceRepository.save(province);
					municipalityRepository.save(m);
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ResponseEntity<String>("Registered", HttpStatus.OK);
	}

	public Map<Object, Province> readProvince() {
		Scanner sc = null;
		Map<Object, Province> provinces = new HashMap<Object, Province>();
		try {
			sc = new Scanner(new File("src\\main\\resources\\static\\province-italiane.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		sc.useDelimiter(",");
		while (sc.hasNext()) {
			String[] province = sc.nextLine().split(";");
			if (!province[0].equals("Sigla")) {
				provinces.put(province[1], new Province(province[0], province[1]));
				System.out.println(province[1].toLowerCase());
			}
		}
		sc.close();
		return provinces;
	}
}