package com.androprex.jcagro.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.androprex.jcagro.dto.AuthanticationResponse;
import com.androprex.jcagro.dto.LoginRequest;
import com.androprex.jcagro.dto.MobileNo;
import com.androprex.jcagro.dto.OtpVerify;
import com.androprex.jcagro.dto.RegisterRequest;
import com.androprex.jcagro.model.NotificationOtp;
import com.androprex.jcagro.model.User;

import com.androprex.jcagro.repo.UserRepository;
import com.androprex.jcagro.security.JwtProvider;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService  {
	
	
	
	private final PasswordEncoder encoder;
	  final UserRepository userRepository;
	
	private final OtpService  otpService;
	
	private final OTPContentBuilder builder;
	

	
	private final AuthenticationManager manager;
	
	private final JwtProvider jwtprovider;
	
	
	
	@Transactional
	public void addUser(RegisterRequest registerRequest)
	{
		User user = new User();
		user.setName(registerRequest.getName());
        user.setCust(registerRequest.getCustId());
		user.setState(registerRequest.getState());
		user.setType(registerRequest.getType());
		user.setPassword(encoder.encode(registerRequest.getPassword()));
		user.setMobile(registerRequest.getMobile());
		user.setCreated(Instant.now());
		user.setEnabled(false);
		
		userRepository.save(user);
	    
		
	}
	 public String verifyAccount(OtpVerify verify) {
		 

			final String SUCCESS = "Entered Otp is valid";
			final String FAIL = "Entered Otp is NOT valid. Please Retry!";
			int otpnum =verify.getOtp();
			//Validate the Otp 
			if(otpnum >= 0){
				
			  int serverOtp = builder.getOtp(verify.getMobileno());
			    if(serverOtp > 0){
			      if(otpnum == serverOtp){
			    	  builder.clearOTP(verify.getMobileno());
			    	  fetchUserandEnable(verify);
	                  return ("Entered Otp is valid");
	                
	                } 
			        else {
	                    return SUCCESS;
	                   }
	               }else {
	              return FAIL;
	               }
	             }else {
	                return FAIL;
	         }
			
	    }
	 
	@Transactional 
	private void fetchUserandEnable(OtpVerify verify) {
		
		String mobileno = verify.getMobileno();
		User user =userRepository.findByMobile(mobileno).orElseThrow(() -> new RuntimeException("Mobile no not found"+mobileno));
		user.setEnabled(true);
		userRepository.save(user);
		
	}
	public AuthanticationResponse login(LoginRequest loginRequest) {
		
		Authentication authentication=manager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMobileno(),loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token =jwtprovider.generateToken(authentication);
		
		return new  AuthanticationResponse(token,loginRequest.getMobileno()); 
	}
	
	@Transactional 
	public boolean genrateOtp(MobileNo verify) {
		
		
		 int token = builder.generateOTP(verify.getMobileno());
		 
	     log.info("Activation email sent!! OTP : "+token);
		
		otpService.sendSms(new NotificationOtp("Welcome to JCAgro Your One Time PassWord is  "+token,verify.getMobileno()));
		
		return true;
	}
	
	
	
	

	 
	 

	
	
	
	  
	

}
