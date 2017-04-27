package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.MovieDao;
import com.spring.model.Movie;

import org.springframework.stereotype.Controller;


@Controller 
public class BrowsingController {
	
	@Autowired
	private MovieDao movieDao; 
	
	
	@RequestMapping("/browse-titles={condition}")
		public ModelAndView titleBrowsing(@PathVariable("condition") String browserTerm) {
			List<Movie> listMovies = movieDao.getMovieListWhereTitlesStartWith(browserTerm);
			ModelAndView model = new ModelAndView("index");
			model.addObject("listMovies", listMovies);
			return model; 
		}
	
	@RequestMapping("/genre={condition}")
	public ModelAndView genreBrowsing(@PathVariable("condition") String genre) {
		List<Movie> listMovies = movieDao.getMovieListWithGenre(genre);
		ModelAndView model = new ModelAndView("index");
		model.addObject("listMovies", listMovies);
		return model; 
	}
	
	
}
