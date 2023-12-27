package com.itstack.hospital.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecpSaveModel 
{
	private String name;	
	private String phone;	
	private String email;
	private String password;	
}
