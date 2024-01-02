package com.itstack.hospital.controllers;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.AppointmentModel;
import com.itstack.hospital.model.PatientUpdateModel;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.services.RecpService;
import com.itstack.hospital.utils.ApiResponse;

@RestController
@RequestMapping("/patient")
public class PatientController 
{
	@Autowired
	private PatientService patService;
	@Autowired
	private RecpService recpService;
	
	@GetMapping("/self")
	public ApiResponse getSelfInfo() 
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)principal;
		Patient patient = patService.getPatientByUserId(USER);
		
		return new ApiResponse(true,"Patient Info",patient);
	}
	
	@PatchMapping("/update/{pid}")
	public ApiResponse updatePatient(@PathVariable(name = "pid") Integer pid,
				@RequestBody PatientUpdateModel model) 
	{
		Patient patient = patService.getById(pid);
		if(patient==null) {
			return new ApiResponse(false, "Patient Not Found !", "Wrong Patient ID !");
		}else 
		{
			try {
				patient.updateByModel(model);
				patService.updatePatient(patient);
				return new ApiResponse(true, "Patient Updated Successfully !");
			} catch (ParseException e) {
				return new ApiResponse(false, "Wrong Patient DOB  !", e.getMessage());
			}catch(Exception ex) {
				return new ApiResponse(false, "Patient Update Failed  !", ex.getMessage());
			}
		}
	}
	
	@PostMapping("/book_appointment")
	public ApiResponse bookAppointment(@RequestBody AppointmentModel model) 
	{
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		final User USER = (User)principal;
		Patient patient = patService.getPatientByUserId(USER);
		model.setPatient(patient.getPatID());
		
		ApiResponse res =  recpService.bookAppointment(model);
		return res;
	}
}
