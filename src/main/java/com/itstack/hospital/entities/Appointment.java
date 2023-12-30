package com.itstack.hospital.entities;

import java.util.Date;

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
@Table(name = "appointment")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment 
{
	@Id
	@Column(name = "app_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer appID;
	
	@ManyToOne
	@JoinColumn(name = "patient")
	private Patient patient;
	
	@Column(name = "book_date",nullable = false)
	private Date bookingDate;
	
	@Column(name = "app_date",nullable = false)
	private Date appointmentDate;
	
	@ManyToOne
	@JoinColumn(name = "doctor")
	private Doctor doctor;
	
	@Column(name = "status",nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "booked_by",nullable = true)
	private Receptionist receptionist;

	public Appointment(Patient patient, Date bookingDate, Date appointmentDate, Doctor doctor, String status,
			Receptionist receptionist) {
		super();
		this.patient = patient;
		this.bookingDate = bookingDate;
		this.appointmentDate = appointmentDate;
		this.doctor = doctor;
		this.status = status;
		this.receptionist = receptionist;
	}
	
	
}
