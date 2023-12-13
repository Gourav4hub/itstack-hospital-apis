package com.itstack.hospital.entities;

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
@Table(name = "receptionist")

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receptionist 
{
	@Id
	@Column(name = "rec_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recID;
	
	@Column(name = "name",nullable = false)
	private String name;
	
	@Column(name = "phone",nullable = false,unique = true)
	private String phone;
	
	@Column(name = "active_status",nullable = false)
	private Boolean activeStatus;
	
	@ManyToOne
	@JoinColumn(name = "user")
	private User user;
}
