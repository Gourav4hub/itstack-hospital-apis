package com.itstack.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.config.JwtTokenUtil;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.LoginModel;
import com.itstack.hospital.model.LoginResponse;
import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.model.UnRegAppointmentModel;
import com.itstack.hospital.services.DocService;
import com.itstack.hospital.services.PatientService;
import com.itstack.hospital.services.UserService;
import com.itstack.hospital.utils.ApiResponse;
import com.itstack.hospital.utils.SystemConstants;

@RestController
@RequestMapping("/web")
public class WebController 
{
	@Autowired
	private PatientService patientService;
	
	@Autowired
	private UserService useService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private DocService docService;
	
	
	@GetMapping("/doctors")
	public ApiResponse loadDoctors() 
	{
		return new ApiResponse(true, "All Doctors Data", docService.listAll());
	}
	
	@PostMapping("/login")
	public ApiResponse loginUser(@RequestBody LoginModel loginModel) 
	{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginModel.getEmail(),loginModel.getPassword()));

			User user =  (User) useService.loadUserByUsername(loginModel.getEmail());
			
			final String token = jwtTokenUtil.generateToken(user);
			
			LoginResponse lp = new LoginResponse(user.getEmail(), token, user.getRole());
			
			return new ApiResponse(true, "Login Success !", lp);
		}catch(Exception ex) {
			return new ApiResponse(false, "Login Failed !", null,ex.getMessage());
		}
	}
	
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
	
	@GetMapping("/accessDenied")
	public ApiResponse accessDenied()
	{
		return new ApiResponse(false, "Unauthorized Access !","Authorization Failed !");
	}
	
	@PostMapping("/self_appointment")
	public ApiResponse appointment(@RequestBody UnRegAppointmentModel model) 
	{
		ApiResponse res = patientService.unRegAppointment(model,null);
		return res;
	}
}
