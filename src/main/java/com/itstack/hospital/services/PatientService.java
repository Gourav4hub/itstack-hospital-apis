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
import com.itstack.hospital.model.UnRegAppointmentModel;
import com.itstack.hospital.repositories.AppointmentRepo;
import com.itstack.hospital.repositories.DocRepo;
import com.itstack.hospital.repositories.PatientRepo;
import com.itstack.hospital.repositories.RecpRepo;
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
	private DocRepo docRepo;
	@Autowired
	private AppointmentRepo appRepo;
	@Autowired
	private RecpRepo recRepo;

	
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

	public ApiResponse unRegAppointment(UnRegAppointmentModel model,Receptionist rec) 
	{
		try {
			Patient pat = null;
			Optional<Patient> patientOP = patientRepo.findByPhone(model.getPhone());
			
			if(patientOP.isPresent()) 
			{
				pat = patientOP.get();
			}else {
				pat = new Patient(model.getName(), model.getPhone());
				pat = patientRepo.save(pat);
			}			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date appointmentDate = sdf.parse(model.getAppointmentDate());
			Date bookingDate = new Date();
			
			Optional<Doctor> opD = docRepo.findById(model.getDoctor());
			if(opD.isPresent())
			{
				Appointment app = new Appointment(pat, bookingDate, appointmentDate, opD.get(), "Pending", rec);
				appRepo.save(app);
				return new ApiResponse(true, "Appointment Saved !");
			}else {
				return new ApiResponse(false, "Appointment Failed !" , "Wrong Doctor !");
			}
		}catch(Exception ex) 
		{
			return new ApiResponse(false, "Appointment Failed !" , ex.getMessage());
		}
	}

	
}
