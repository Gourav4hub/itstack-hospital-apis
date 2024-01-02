package com.itstack.hospital.model;

import java.util.Date;

import com.itstack.hospital.entities.Doctor;

import lombok.Data;

@Data
public class UnRegAppointmentModel 
{
	private String name;
	private String phone;
	private String appointmentDate;
	private Integer doctor;	
}
