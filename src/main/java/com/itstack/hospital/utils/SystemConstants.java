package com.itstack.hospital.utils;

import java.util.HashMap;

public interface SystemConstants 
{
	String GENDER[] = {
			"Male" , "Female" , "Others"
	};
	
	String SPECIALIZATION[] = {
			
	};
	
	String TEST_TYPES[] = {
			"Blood" , "Urine" , "X-RAY" , "CT-Scan" , "SonoGraphy" 
	};
	
	String TEST_STATUS[] = {
			"Positive" , "Negative"
	};
	
	String PAYMENT_MODES[] = {
			"Cash" , "UPI" , "Card"
	};
	
	String APPOINTMENT_STATUS[] = {
			"Pending" , "Approve" , "Reject"
	};
	
	static HashMap<String, String[]>  getConstants() 
	{
		HashMap<String, String []> data = new HashMap<>();
		data.put("gender", GENDER);
		data.put("specialization", SPECIALIZATION);
		data.put("test_types", TEST_TYPES);
		data.put("test_status", TEST_STATUS);
		data.put("payment_modes", PAYMENT_MODES);
		data.put("appointment_status", APPOINTMENT_STATUS);
		return data;
	}
}
