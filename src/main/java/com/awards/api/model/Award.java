package com.awards.api.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel("Model Award")
@Table(name = "awards")
@Entity
public class Award{
	public Award(){};
	public Award(long id, long code, String description, long count){
		this.id = id;
		this.code = code;
		this.description = description;
		this.count = count;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	@ApiModelProperty(value = "the award id", required = true)
    private long id;
	
	@ApiModelProperty(value = "the award code", required = true)
	@Column(name="code", nullable = false)
    private long code;
	
	@ApiModelProperty(value = "the award description", required = true)
	@Size(min = 1, max = 255)
	@Column(name="description", nullable = false)
    private String description;
	
	@ApiModelProperty(value = "the award count", required = true)
	@Size(min = 1, max = 255)
	@Column(name= "count", nullable = false)
    private long count;
	
	@OneToMany(mappedBy="award")
	@JsonIgnore
    private Set<People> people;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public Set<People> getPeople() {
		return people;
	}
	public void setPeople(Set<People> people) {
		this.people = people;
	}
	
	

}
