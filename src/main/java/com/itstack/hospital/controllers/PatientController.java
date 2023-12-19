package com.itstack.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.utils.ApiResponse;

@RestController
@RequestMapping("/patient")
public class PatientController 
{
	@Autowired
	private PatientService patService;
	
	@GetMapping("/self")
	public ApiResponse getSelfInfo() 
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)principal;
		Patient patient = patService.getPatientByUserId(USER);
		
		return new ApiResponse(true,"Patient Info",patient);
	}
}
