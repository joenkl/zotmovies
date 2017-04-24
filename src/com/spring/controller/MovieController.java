package com.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.spring.dao.*;
import com.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes; 

@Controller 
public class MovieController {

	@Autowired
	private MovieDao movieDao; 
	
	@RequestMapping(value="/searchForm")
	public String showSearchForm()
	{
		return "search"; 
	}
	
	@RequestMapping(value="/search")
	
	public ModelAndView searchForMovies(
			@RequestParam(value="title", required=false) String title, 
			@RequestParam(value="first_name", required=false) String first_name, 
			@RequestParam(value="last_name", required=false) String last_name, 
			@RequestParam(value="year", required=false) String year, 
			@RequestParam(value="director", required=false) String director, 
			
			RedirectAttributes redir)
	{
		
		System.out.println(title);
	
		if(title.isEmpty() && first_name.isEmpty() && last_name.isEmpty() 
				&& year.isEmpty() && director.isEmpty() )
		{
			System.out.println("tittle is " + title);
			//return all movie list 
			return new ModelAndView("index");
		}
		
		else
		{
			if(year.isEmpty())
				year = "-1";
			
			ModelAndView model = new ModelAndView("index");
			model.addObject("listMovies", 
					movieDao.getMovieListWithSearch(title, Integer.parseInt(year), director, first_name, last_name));
			
			return model;
		}
		
		
		
		
	}
	
	
}
