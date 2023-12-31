package com.itstack.hospital.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itstack.hospital.model.DoctorModel;
import com.itstack.hospital.model.RecpSaveModel;
import com.itstack.hospital.services.DocService;
import com.itstack.hospital.services.RecpService;
import com.itstack.hospital.utils.ApiResponse;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	private RecpService recpService;
	@Autowired
	private DocService docService;
	
	@PostMapping("/save_recp")
	public ApiResponse saveRecp(@RequestBody RecpSaveModel model) 
	{
		ApiResponse response =  recpService.saveRecp(model);
		return response;
	}
	
	@PostMapping("/save_doc")
	public ApiResponse saveDoc(@RequestBody DoctorModel model) 
	{
		ApiResponse response =  docService.saveDoc(model);
		return response;
	}
}
