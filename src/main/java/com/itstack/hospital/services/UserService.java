package com.itstack.hospital.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itstack.hospital.entities.User;
import com.itstack.hospital.model.LoginModel;
import com.itstack.hospital.repositories.UserRepo;
import com.itstack.hospital.utils.ApiResponse;

@Service
public class UserService implements UserDetailsService
{
	@Autowired
	private UserRepo userRepo;


	public User saveUser(String email, String password,String role) 
	{
		User user = new User(email, password, role, true);
		user = userRepo.save(user);
		return user;
	}

	public User getById(Integer userid) {
		return userRepo.findById(userid).get();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserDetails obj =  userRepo.findByEmail(username).get();
		return obj;
	}
}
