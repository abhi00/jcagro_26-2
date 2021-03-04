package com.androprex.jcagro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.androprex.jcagro.dto.AuthanticationResponse;
import com.androprex.jcagro.dto.LoginRequest;
import com.androprex.jcagro.dto.MobileNo;
import com.androprex.jcagro.dto.OtpVerify;
import com.androprex.jcagro.dto.RegisterRequest;
import com.androprex.jcagro.model.User;
import com.androprex.jcagro.service.AuthService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
	
	private  final AuthService authService;

	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody RegisterRequest registerRequest)
	{
		authService.addUser(registerRequest);
		
		return new ResponseEntity<>("User Register Successfully",HttpStatus.OK);
	}
	
	@PostMapping("/validateotp")
	public ResponseEntity<String> validateUser(@RequestBody OtpVerify verify)
	{
		String data =authService.verifyAccount(verify);
		
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	
	@PostMapping("/genrateotp")
	public ResponseEntity<Boolean> genrateOTP(@RequestBody MobileNo verify)
	{
		boolean data =authService.genrateOtp(verify);
		
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	
	
	@PostMapping("/login")
	public AuthanticationResponse login(@RequestBody LoginRequest loginRequest)
	{
	return authService.login(loginRequest);	
	}
	
	
}
