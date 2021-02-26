package com.androprex.jcagro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthanticationResponse {
	
	private String authanticationToken;
	private String mobileno;
	

}
