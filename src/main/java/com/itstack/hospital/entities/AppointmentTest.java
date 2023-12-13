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
@Table(name = "appointment_test")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentTest 
{
	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer testID;
	
	@ManyToOne
	@JoinColumn(name = "appointment")
	private Appointment appointment;
	
	@Column(name = "types",nullable = false)
	private String types;
	
	@Column(name = "status",nullable = false)
	private String status;
	
	@Column(name = "report",nullable = false)
	private String report;
	
	@ManyToOne
	@JoinColumn(name = "upload_by")
	private Receptionist receptionist;
}
