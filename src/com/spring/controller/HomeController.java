
package com.spring.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
 
import java.util.Random;

import com.spring.dao.*;
import com.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; 
@Controller
public class HomeController {
	
	@Autowired
	private MovieDao movieDao; 
	
	@RequestMapping(value={"", "/","/login"})
	public String login()
	{	
		//hitting home page => sestting session:
		
		return "login"; 
	}

	@RequestMapping("/index")
	public ModelAndView home() throws IOException {
		List<Movie> listMovies = movieDao.getMovieList();
	public ModelAndView home(
			@RequestParam(value = "page", required = false) String page
			) throws IOException {
		
		if(page == null)
		{
			page = "1";
			if(!tryParseInt(page))
			{
				return new ModelAndView("404-page");
			}
			
		}
		
		else
		{
			if(page.isEmpty())
				page = "1";
		}
		List<Movie> listMovies = movieDao.getMovieList(Integer.parseInt(page));
		ModelAndView model = new ModelAndView("index");
		
		//shuffle list:
		long seed = System.nanoTime();
		Collections.shuffle(listMovies, new Random(seed));
		
		model.addObject("listMovies", listMovies);
		model.addObject("activePage", page);
		model.addObject("currentPage", "index");
		
		if(listMovies.size() < 12)
			model.addObject("lastPage", true);
		else
			model.addObject("lastPage", false);
		return model; 
	}

	@RequestMapping("/titles")
	public String titles() {
		return ("titles");
	}
	
	@RequestMapping("/checkout")
	public String checkout() {
		return ("checkout");
	}
	
	@RequestMapping("/testdb")
	public String testPage() {
		return ("test-db");
	}
	
	private Boolean tryParseInt(String number) {
		try {
			Integer.parseInt(number);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
