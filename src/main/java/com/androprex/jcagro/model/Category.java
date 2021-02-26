package com.androprex.jcagro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "category")
@Getter
@Setter
public class Category extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name="title")
    private String title;

    @NotNull
    @Size(max = 250)
    @Column(name="img_url")
    private String imgurl;
    
   
}