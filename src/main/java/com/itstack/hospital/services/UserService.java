package com.itstack.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.User;
import com.itstack.hospital.repositories.UserRepo;

@Service
public class UserService 
{
	@Autowired
	private UserRepo userRepo;

	public User saveUser(String email, String password) 
	{
		User user = new User(email, password, "patient", true);
		user = userRepo.save(user);
		return user;
	}
}
