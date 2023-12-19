package com.itstack.hospital.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itstack.hospital.entities.Patient;
import com.itstack.hospital.entities.User;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer>
{
	Optional<Patient> findByUser(User user);
}
