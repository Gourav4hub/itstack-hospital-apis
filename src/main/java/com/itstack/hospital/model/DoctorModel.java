package com.itstack.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorModel {
	private String name;	
	private String phone;
	private String specialization;
	private String email;
	private String password;
}
