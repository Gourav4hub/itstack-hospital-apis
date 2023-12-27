package com.itstack.hospital.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itstack.hospital.model.PatientUpdateModel;

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
@Table(name = "patient")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient 
{
	@Id
	@Column(name = "pat_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patID;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "phone",nullable = false,unique = true)
	private String phone;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "gender")
	private String gender;	
	
	@ManyToOne
	@JoinColumn(name = "user")
	@JsonIgnore
	private User user;

	public Patient(String name, String phone, String address, Date dob, String gender, User user) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.user = user;
	}
	
	public void updateByModel(PatientUpdateModel model) throws ParseException 
	{
		if(model.getName()!=null) this.name = model.getName();
		if(model.getPhone()!=null) this.phone = model.getPhone();
		if(model.getAddress()!=null) this.address = model.getAddress();
		if(model.getDob()!=null) 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = sdf.parse(model.getDob());
			this.dob = dob;
		}
	}
	
}
