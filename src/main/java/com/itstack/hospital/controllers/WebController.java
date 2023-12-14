package com.itstack.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.utils.ApiResponse;
import com.itstack.hospital.utils.SystemConstants;

@RestController
@RequestMapping("/web")
public class WebController 
{
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/getconstants")
	public ApiResponse loadConstants() 
	{
		return new ApiResponse(true, "All Constants Data", SystemConstants.getConstants());
	}
	
	
	@PostMapping("/reg_patient")
	public ApiResponse registerPatient(@RequestBody PatientRegModel patientModel) 
	{
		ApiResponse response =  patientService.savePatient(patientModel);
		return response;
	}
}
