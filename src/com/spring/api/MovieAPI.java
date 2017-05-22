package com.spring.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.MovieDao;
import com.spring.model.Movie;

@RestController
public class MovieAPI {
	
	@Autowired
	MovieDao movieDao;
	
	@GetMapping(value="/api/movielist")
	public List<Movie> getMovieList(){
		List<Movie> result = movieDao.getAllMovie();
		return result; 
		
	}
	
	@GetMapping(value="/api/movieTitleList")
	public List<String> getMovieTitleList(){
		List<String> result = movieDao.getAllMovieTitle();
		return result; 
		
	}

}
