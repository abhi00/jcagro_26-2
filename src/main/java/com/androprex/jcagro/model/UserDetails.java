package com.androprex.jcagro.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

//@Data
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "user_details")
//@DynamicUpdate
public class UserDetails  {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userDetailsId;
	
	
	private String email;
	
	@NonNull
	@Lob
	private String adress;
	
	private String landmark;
	
	
	private Date dob; 
	
	private String gender;
	
	private String idProofType;
	
	
	private String idProofNumber;
	
	private String id_url1;
	
	private String id_url2;
	
	
	
	

}
