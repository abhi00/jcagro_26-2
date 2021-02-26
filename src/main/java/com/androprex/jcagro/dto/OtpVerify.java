package com.androprex.jcagro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerify {
	
	private int otp;
	

	private String mobileno;

}
