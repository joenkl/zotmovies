package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;


import com.spring.dao.AdminDao;
import com.spring.model.Admin;

@Controller
public class AdminController {
	@Autowired
	private AdminDao adminDao;
	
	@RequestMapping(value="/_dashboard")
	public ModelAndView admin(HttpServletRequest request, RedirectAttributes redir)
	{
		HttpSession session = request.getSession(true);
		if (session.getAttribute("isAdmin") == null)
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/_dashboard-login");
			return model;
		}
		
		
		ModelAndView model = new ModelAndView("admin-dashboard");
		return model;
	}
	
	@RequestMapping(value="/_dashboard-login")
	public String adminLogin(HttpServletRequest request, RedirectAttributes redir)
	{
		HttpSession session = request.getSession(true);
		Integer adminLogin = (Integer) (session.getAttribute("isAdmin"));
		
		if (adminLogin == null)
			return "admin-login";
		else
			return "redirect:/_dashboard";
	}
	
	@RequestMapping(value="/_process-dashboard-login", method = RequestMethod.POST)
	public ModelAndView processAdminLogin(HttpServletRequest request, RedirectAttributes redir)
	{
		Admin admin = adminDao.getAdminInfo(request.getParameter("email"), request.getParameter("password"));
		if (admin == null)
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/_dashboard-login");
			redir.addFlashAttribute("message", "Invalid Email/Password");
			return model;
		}
		else{
			HttpSession session = request.getSession(true);
			int adminLogin = new Integer(1);
			
			session.setAttribute("isAdmin", adminLogin);
			session.setAttribute("adminFN", admin.getAdminFullName());
			
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/_dashboard");
			return model;
		}
	}
	
	
	@RequestMapping(value = "/_dashboard-logout", method = RequestMethod.GET)
	public ModelAndView processLogout(HttpServletRequest request, RedirectAttributes redir) {

		HttpSession session = request.getSession(true);
		session.removeAttribute("isAdmin");
		session.removeAttribute("adminFN");
		return new ModelAndView("redirect:/_dashboard-login");
	}
}
