package com.spring.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/_add-new-star")
	public String addNewStar() {
		return ("_add-new-star");
	}
	
	
	@RequestMapping(value="/_add-new-movie")
	public String addNewMovieProc() {
		return ("_add-new-movie");
	}
	
	@RequestMapping(value="/_edit-movie")
	public String editMovieProc() {
		return ("_edit-movie");
	}
	
	@RequestMapping(value = "/_movie-confirmation", method = RequestMethod.POST)
	public ModelAndView addNewMovie(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "director", required = true) String director,
			@RequestParam(value = "year", required = true) Integer year,
			@RequestParam(value = "banner_url", required = false) String banner_url,
			@RequestParam(value = "trailer_url", required = false) String trailer_url,

			@RequestParam(value = "genre", required = false) String genre,

			@RequestParam(value = "first_name", required = false) String starFN,
			@RequestParam(value = "last_name", required = true) String starLN,
			@RequestParam(value = "dob", required = true) java.sql.Date starDob,
			@RequestParam(value = "photo_url", required = false) String starPhotoURL) {
		ModelAndView model = new ModelAndView("_movie-confirmation");

	
		if (banner_url == null)
			banner_url = "";
		if (trailer_url == null)
			trailer_url = "";

		String msg = adminDao.addMovieProcedure(title, year, director, banner_url, trailer_url, starFN, starLN, starDob,
				starPhotoURL, genre);

		model.addObject("msg", msg);

	

		return model;

	}
	
	@RequestMapping(value="/_new-star-confirmation", method = RequestMethod.POST)
	public ModelAndView addNewStarWithName( @RequestParam(value="first_name") String first_name,
			@RequestParam(value="last_name") String last_name, @RequestParam(value="dob") java.sql.Date dob,
			@RequestParam(value="photo_url") String photo_url){
		
		if (first_name.isEmpty() && last_name.isEmpty())
		{
			ModelAndView model = new ModelAndView("_new-star-confirmation");
			model.addObject("msg", "Both First Name and Last Name cannot be empty.\n");
			return model;
		}
		
		ModelAndView model = new ModelAndView("_new-star-confirmation");
		if (last_name.isEmpty())
		{
			last_name = first_name;
			first_name ="";
			model.addObject("msg", "Single Name detected!\n "
					+ "First Name will be blank, and Last Name is " + last_name);
		}
		
		if (photo_url.isEmpty())
			photo_url ="https://c1.staticflickr.com/5/4179/34574108456_1a5a4ef007.jpg";
		
		System.out.println(dob);
		
		
		adminDao.addNewStar(first_name, last_name, dob, photo_url);
		
		model.addObject("msg", "Successfully add " + first_name + " " + last_name 
								+ " into database");
		return model;
	}
}
