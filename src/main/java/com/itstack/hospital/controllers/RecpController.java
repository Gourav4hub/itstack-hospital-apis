package com.itstack.hospital.controllers;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.PatientUpdateModel;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.utils.ApiResponse;

@RestController
@RequestMapping("/recep")
public class RecpController 
{
	@Autowired
	private PatientService patService;
	
	@GetMapping("/list_patient")
	public ApiResponse getPatientList() 
	{
		List<Patient> list = patService.listAll();
		
		return new ApiResponse(true,"Patient Records",list);
	}
	
	@GetMapping("/search_patient/{phone}")
	public ApiResponse searchPatientByPhone(@PathVariable(name = "phone") String phone) 
	{
		Patient ob = patService.getByPhone(phone);
		if(ob==null)
			return new ApiResponse(false,"Patient Not Found !");
		else	
			return new ApiResponse(true,"Patient Record",ob);
	}
}
