package com.itstack.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itstack.hospital.entities.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Integer>
{

}
