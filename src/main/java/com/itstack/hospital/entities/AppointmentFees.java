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
@Table(name = "appointment_fees")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentFees 
{
	@Id
	@Column(name = "fees_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feesID;
	
	@ManyToOne
	@JoinColumn(name = "appointment")
	private Appointment appointment;
	
	@Column(name = "date",nullable = false)
	private Date date;
	
	@Column(name = "amount",nullable = false)
	private Float amount;

	@Column(name = "mode",nullable = false)
	private String mode;
	
	@ManyToOne
	@JoinColumn(name = "receive_by")
	private Receptionist receptionist;
}
