package com.itstack.hospital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itstack.hospital.entities.Appointment;
import com.itstack.hospital.entities.Doctor;
import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Integer>
{
	
}
