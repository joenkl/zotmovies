package com.spring.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.CustomerDao;

@RestController
public class LoginAPI {
	
	@Autowired 
	CustomerDao customerDao; 
	
	//String email, String password
	@RequestMapping(method=RequestMethod.POST, value="/api/validateCustomer")
	public Boolean validateCustomer(
			@RequestParam(value = "username", required = true) String username,
			@RequestParam(value = "password", required = true) String password
			){
		
			if(username.isEmpty() || password.isEmpty()){
				return false;
			}
			
			else{
				return customerDao.isCustomer(username, password);
			}
		
		
		//return false; 
	}
	

}
