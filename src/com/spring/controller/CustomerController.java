package com.spring.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dao.CustomerDao;
import com.spring.model.Movie;

@Controller 
public class CustomerController {
	
	@Autowired
	CustomerDao customerdao;
	
	@RequestMapping(value="/processLoginForm",method=RequestMethod.POST)
	public ModelAndView processLoginForm(HttpServletRequest request, RedirectAttributes redir){
		Boolean isCustomer = customerdao.isCustomer(request.getParameter("email")
				, request.getParameter("password"));
		
		if(isCustomer)
		{
			//todo: change session 
			HttpSession session = request.getSession(true);
			Integer login = (Integer) (session.getAttribute("login"));
			if(login == null)
			{
				login = new Integer(1);
				session.setAttribute("login", login);
			}
			
			return new ModelAndView("redirect:/index"); 
		}
		
		else
		{
			
			//todo: change session 
			HttpSession session = request.getSession(true);
			Integer login = (Integer) (session.getAttribute("login"));
			if(login == null)
			{
				login = new Integer(0);
				session.setAttribute("login", login);
			}
			
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/login");
			redir.addFlashAttribute("message", "Invalid Email/Password combination. Please try again!");
			return model; 
		}
		
	}

}
