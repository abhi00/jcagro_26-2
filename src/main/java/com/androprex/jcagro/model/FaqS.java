package com.androprex.jcagro.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="faqs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FaqS implements Serializable
{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="question")
	private String question;
	@Column(name="answer")
	private String answer;
	
	
	    



	



	
	
}
