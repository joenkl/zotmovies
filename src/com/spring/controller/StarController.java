package com.spring.controller;

import java.util.Date;
import java.util.HashMap;
import java.sql.SQLData;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dao.MovieDao;
import com.spring.dao.StarDao;
import com.spring.model.Movie;
import com.spring.model.Star;

import org.springframework.stereotype.Controller;


@Controller 
public class StarController {
	
	@Autowired
	StarDao starDao; 
	
	@Autowired
	MovieDao movieDao;
	
	@RequestMapping(value="/getStarInfo")
	public ModelAndView getStarInfo(@RequestParam(value="id") String id,
			@RequestParam(value="fn") String fn,
			@RequestParam(value="ln") String ln,
			@RequestParam(value="dob") String dob,
			@RequestParam(value="photo_url") String photo_url
			) throws ParseException
	{

		Star star = new Star(Integer.parseInt(id), fn, ln, dob, photo_url);
		ModelAndView model= new ModelAndView("star-info");
		model.addObject("star", star);
		
		List<Movie> listMovies =  movieDao.getMovieListWithStar(Integer.parseInt(id));
		
		model.addObject("listMovies", listMovies);
		
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
		
		
		starDao.addNewStar(first_name, last_name, dob, photo_url);
		
		model.addObject("msg", "Success fully add " + first_name + " " + last_name 
								+ " into database");
		return model;
	}
}
