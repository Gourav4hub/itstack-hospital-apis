package com.itstack.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PatientRegModel 
{
	private String name;	
	private String phone;	
	private String email;
	private String password;
	private String address;	
	private String dob;	
	private String gender;	
}
