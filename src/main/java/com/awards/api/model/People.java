package com.awards.api.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Model People")
@Table(name = "people")
@Entity
public class People{
	public People(){};
	public People(long id, String typedocument, String document, String name, String lastname, 
			Date birthdate){
		this.id = id;
		this.typedocument = typedocument;
		this.document = document;
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@ApiModelProperty(value = "the people id", required = true)
    private long id;
	
	@ApiModelProperty(value = "the people typedocument", required = true)
	@Column(name="typedocument", nullable = false)
    private String typedocument;
	
	@ApiModelProperty(value = "the people document", required = true)
	@Size(min = 1, max = 255)
	@Column(name="document", nullable = false)
    private String document;
	
	@ApiModelProperty(value = "the people name", required = true)
	@Size(min = 1, max = 255)
	@Column(name= "name", nullable = false)
    private String name;
	
	@ApiModelProperty(value = "the people lastname", required = true)
	@Size(min = 1, max = 255)
	@Column(name= "lastname", nullable = false)
    private String lastname;
	
	@ApiModelProperty(value = "the people birthdate", required = true)
	@Temporal(TemporalType.DATE)
	@Column(name= "birthdate", nullable = false)
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date birthdate;
	
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name="award_id", nullable=true)
	private Award award;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTypedocument() {
		return typedocument;
	}
	public void setTypedocument(String typedocument) {
		this.typedocument = typedocument;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public Award getAward() {
		return award;
	}
	public void setAward(Award award) {
		this.award = award;
	}
	
}
