package com.itstack.hospital.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.entities.Appointment;
import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.Receptionist;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.AppointmentModel;
import com.itstack.hospital.model.PatientUpdateModel;
import com.itstack.hospital.model.UnRegAppointmentModel;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.services.RecpService;
import com.itstack.hospital.utils.ApiResponse;

@RestController
@RequestMapping("/recep")
public class RecpController 
{
	@Autowired
	private PatientService patService;
	@Autowired
	private RecpService recpService;
	@Autowired
	private PatientService patientService;
	
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
	
	@PostMapping("/book_appointment")
	public ApiResponse bookAppointment(@RequestBody AppointmentModel model) 
	{
		ApiResponse res =  recpService.bookAppointment(model);
		return res;
	}
	
	@PostMapping("/unreg_appointment")
	public ApiResponse appointment(@RequestBody UnRegAppointmentModel model) 
	{
		Receptionist rec = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(principal!=null) 
		{
			final User loginUser = (User)principal;
			
			if(loginUser.getRole().equals("ROLE_RECP")) {
				rec = recpService.findByUser(loginUser).get();
			}
		}		
		ApiResponse res = patientService.unRegAppointment(model,rec);
		return res;
	}
	
	
	@GetMapping("/list_appointment")
	public ApiResponse getAppointmentList() 
	{
		List<Appointment> list = recpService.listAllAppointments();
		
		return new ApiResponse(true,"Appointment Records",list);
	}
}
