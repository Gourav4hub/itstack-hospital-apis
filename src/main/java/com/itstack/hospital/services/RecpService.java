package com.itstack.hospital.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.Receptionist;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.model.RecpSaveModel;
import com.itstack.hospital.repositories.PatientRepo;
import com.itstack.hospital.repositories.RecpRepo;
import com.itstack.hospital.utils.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public class RecpService 
{
	@Autowired
	private RecpRepo recpRepo;
	@Autowired
	private UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public ApiResponse saveRecp(RecpSaveModel model) 
	{
		ApiResponse response = null;
		try {
			User user = userService.saveUser(model.getEmail(),passwordEncoder.encode(model.getPassword()),"ROLE_RECP");
					
			Receptionist obj = new Receptionist(model.getName(), model.getPhone(), true, user);
			recpRepo.save(obj);
			
			response = new ApiResponse(true, "Receptionist Saved !");
		}catch(Exception ex) {
			System.err.println(ex.getMessage());
			response = new ApiResponse(false, "Receptionist Save Failed !", ex.getMessage());
		}		
		return response;
	}
}
