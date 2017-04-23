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
	
	
	@RequestMapping("/browsing/title-start-with={condition}")
		public ModelAndView titleBrowsing(@PathVariable("condition") String browserTerm) {
			List<Movie> listMovies = movieDao.getMovieListWhereTitlesStartWith(browserTerm);
<<<<<<< HEAD
			ModelAndView model = new ModelAndView("search-result");
=======
			ModelAndView model = new ModelAndView("index");
>>>>>>> c8faefbdb09912298e1b348cb9ea9ed0dd863d43
			model.addObject("listMovies", listMovies);
			return model; 
		}
}
