package com.itstack.hospital.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "doctor")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor 
{
	@Id
	@Column(name = "doc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer docID;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "phone",nullable = false,unique = true)
	private String phone;
	
	@Column(name = "specialization",nullable = false)
	private String specialization;	
	
	@Column(name = "active_status",nullable = false)
	@JsonIgnore
	private Boolean activeStatus;
	
	@ManyToOne
	@JoinColumn(name = "user")
	@JsonIgnore
	private User user;

	public Doctor(String name, String phone, String specialization, Boolean activeStatus, User user) {
		super();
		this.name = name;
		this.phone = phone;
		this.specialization = specialization;
		this.activeStatus = activeStatus;
		this.user = user;
	}
	
	
}
