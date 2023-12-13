package com.itstack.hospital.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.utils.ApiResponse;
import com.itstack.hospital.utils.SystemConstants;

@RestController
@RequestMapping("/web")
public class WebController 
{
	@GetMapping("/getconstants")
	public ApiResponse loadConstants() 
	{
		return new ApiResponse(true, "All Constants Data", SystemConstants.getConstants());
	}
	
}
