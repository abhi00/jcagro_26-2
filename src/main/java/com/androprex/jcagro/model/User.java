package com.androprex.jcagro.model;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@NotEmpty(message = "UserName is Required")
    private String cust;
	
	
	@NotEmpty(message = "UserName is Required")
	private String name;
	
	@NotEmpty(message = "UserName is Required")
	private String state;
	
	@NotEmpty(message = "UserName is Required")
	private String type;
	
	@NotEmpty(message = "PassWord is Required")
	private String password;
	
	@NotEmpty(message = "PassWord is Required")
	private String mobile;
	

	
	
	 private Instant created;
	
	private boolean  enabled;
	
	@PrePersist
	public void setCust(){this.cust= new String("pre");
	}
	{
		
	}
	
	
	
	

}
