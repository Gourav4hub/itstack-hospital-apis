package com.itstack.hospital.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.repositories.PatientRepo;
import com.itstack.hospital.utils.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class PatientService 
{
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	//@Transactional(value = TxType.REQUIRES_NEW)
	public ApiResponse savePatient(PatientRegModel patientModel) 
	{
		ApiResponse response = null;
		try {
			User user = userService.saveUser(patientModel.getEmail(),passwordEncoder.encode(patientModel.getPassword()),"ROLE_PATIENT");
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dob = sdf.parse(patientModel.getDob());
			
			Patient patient = new Patient(patientModel.getName(), patientModel.getPhone(), patientModel.getAddress(), dob, patientModel.getGender(), user);
			patientRepo.save(patient);
			
			response = new ApiResponse(true, "Patient Saved !");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			response = new ApiResponse(false, "Patient Save Failed !", ex.getMessage());
		}		
		return response;
	}
	
	public Patient getPatientByUserId(User user) 
	{
		return patientRepo.findByUser(user).get();
	}

	public Patient getById(Integer pid) 
	{
		Optional<Patient> op =  patientRepo.findById(pid);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}

	public void updatePatient(Patient patient) 
	{
		patientRepo.save(patient);		
	}

	public List<Patient> listAll() {
		return patientRepo.findAll();
	}

	public Patient getByPhone(String phone) 
	{
		Optional<Patient> op =  patientRepo.findByPhone(phone);
		if(op.isPresent())
			return op.get();
		else
			return null;
	}
}
