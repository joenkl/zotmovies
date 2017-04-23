package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.CustomerDao;
import com.spring.model.Movie;

@Controller 
public class CustomerController {
	
	@Autowired
	CustomerDao customerdao;
	
	@RequestMapping(value="/processLoginForm",method=RequestMethod.POST)
	public String processLoginForm(HttpServletRequest request, Model model){
		Boolean isCustomer = customerdao.isCustomer(request.getParameter("email")
				, request.getParameter("password"));
		
		if(isCustomer)
		{
			return "index";
		}
		
		else
		{
			model.addAttribute("message", "Invalid Email/Password Combination");
			
			return "login"; 
		}
		
	}

}
