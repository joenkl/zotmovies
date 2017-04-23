package com.spring.controller;

import java.io.IOException;
import java.util.List;
 
import com.spring.dao.*;
import com.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView; 
@Controller
public class HomeController {
	
	@Autowired
	private MovieDao movieDao; 
	
	@RequestMapping(value={"/","/login"})
	public String login()
	{
		return "login"; 
	}

	@RequestMapping("/index")
	public ModelAndView home() throws IOException {
		List<Movie> listMovies = movieDao.getMovieList();
		ModelAndView model = new ModelAndView("index");
		model.addObject("listMovies", listMovies);
		return model; 
	}

	@RequestMapping("/testdb")
	public String testPage() {
		return ("test-db");
	}

}
