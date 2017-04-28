package com.spring.controller;

import java.util.Date;
import java.util.HashMap;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MovieDao;
import com.spring.dao.StarDao;
import com.spring.model.Movie;
import com.spring.model.Star;

import org.springframework.stereotype.Controller;


@Controller 
public class StarController {
	
	@Autowired
	StarDao starDao; 
	
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
		return model; 
	}

	
}
