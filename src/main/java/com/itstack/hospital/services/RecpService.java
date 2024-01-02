package com.itstack.hospital.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.Appointment;
import com.itstack.hospital.entities.Doctor;
import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.Receptionist;
import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.AppointmentModel;
import com.itstack.hospital.model.PatientRegModel;
import com.itstack.hospital.model.RecpSaveModel;
import com.itstack.hospital.repositories.AppointmentRepo;
import com.itstack.hospital.repositories.DocRepo;
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
	@Autowired
	private DocRepo docRepo;
	@Autowired
	private RecpRepo recRepo;
	@Autowired
	private PatientRepo patientRepo;
	@Autowired
	private AppointmentRepo appRepo;
	

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

	public List<Appointment> listAllAppointments() 
	{
		return appRepo.findAll();
	}
	
	public ApiResponse bookAppointment(AppointmentModel model) 
	{
		try {
			Optional<Patient> opP = patientRepo.findById(model.getPatient());
			if(opP.isPresent()) 
			{
				Optional<Doctor> opD = docRepo.findById(model.getDoctor());
				if(opD.isPresent()) 
				{
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date appointmentDate = sdf.parse(model.getAppointmentDate());
					Date bookingDate = new Date();
					
					Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					final User loginUser = (User)principal;
					Receptionist rec = null;
					if(loginUser.getRole().equals("ROLE_RECP")) {
						rec = recRepo.findByUser(loginUser).get();
					}
					
					Appointment app = new Appointment(opP.get(), bookingDate, appointmentDate, opD.get(), "Pending", rec);
					
					appRepo.save(app);
					return new ApiResponse(true, "Appointment Saved !");
				}else {
					return new ApiResponse(false, "Appointment Failed !" , "Wrong Doctor !");
				}
			}else 
			{
				return new ApiResponse(false, "Appointment Failed !" , "Wrong Patient !");
			}
		}catch(Exception ex) {
			return new ApiResponse(false, "Appointment Failed !" , ex.getMessage());
		}
	}

	public Optional<Receptionist> findByUser(User loginUser) {
		return recpRepo.findByUser(loginUser);
	}
}
