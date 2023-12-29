package com.itstack.hospital.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.Doctor;
import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.Receptionist;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.DoctorModel;
import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.model.RecpSaveModel;
import com.itstack.hospital.repositories.DocRepo;
import com.itstack.hospital.repositories.PatientRepo;
import com.itstack.hospital.repositories.RecpRepo;
import com.itstack.hospital.utils.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class DocService 
{
	@Autowired
	private DocRepo docRepo;
	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public ApiResponse saveDoc(DoctorModel model) 
	{
		ApiResponse response = null;
		try {
			User user = userService.saveUser(model.getEmail(),passwordEncoder.encode(model.getPassword()),"ROLE_DOC");
					
			Doctor doc = new Doctor(model.getName(), model.getPhone(), model.getSpecialization(), true, user);
			docRepo.save(doc);
			response = new ApiResponse(true, "Doctor Saved !");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			response = new ApiResponse(false, "Doctor Save Failed !", ex.getMessage());
		}		
		return response;
	}

	public List<Doctor> listAll() {
		return docRepo.findAll();
	}
}
