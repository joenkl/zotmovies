package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController  {
	
	@RequestMapping(value = { "", "/", "/login" })
	public String login(HttpServletRequest request) {
		// hitting home page => setting session:
		String referrer = request.getHeader("Referer");
		request.getSession(true).setAttribute("url_prior_login", referrer);
		
		System.out.println(referrer);
		if(referrer == null)
		{
			request.getSession(true).setAttribute("url_prior_login", "/index");
		}
		return "login";
	}
	
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public ModelAndView processLogout(HttpServletRequest request, RedirectAttributes redir){
		
		HttpSession session = request.getSession(true);
		session.removeAttribute("login");
		session.removeAttribute("customerID");
		session.removeAttribute("customerFN");
		return new ModelAndView("redirect:/login");
	}
}
