package com.crm.model.auth;

import java.util.List;

import lombok.Data;

@Data
public class LoginResponse {

	private final String type = "Bearer";
	private String token;
	private List<String> roles;

}
