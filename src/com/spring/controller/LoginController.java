package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	//need a controller method to process the HTML form
	@RequestMapping("/processLoginForm")
	public String processLoginForm(){
		//todo: check whether log in successful 
		return "redirect:/index"; 
	}
}
