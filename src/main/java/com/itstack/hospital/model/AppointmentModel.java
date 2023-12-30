package com.itstack.hospital.model;

import java.util.Date;

import com.itstack.hospital.entities.Doctor;

import lombok.Data;

@Data
public class AppointmentModel 
{
	private Integer patient;
	private String appointmentDate;
	private Integer doctor;	
}
