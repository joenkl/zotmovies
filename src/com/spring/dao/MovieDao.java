
package com.spring.dao;

import java.util.List;


import com.spring.model.Movie;;

public interface MovieDao {
	
	public List<Movie> getMovieList();
	
	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith);
	
	public List<Movie> getMovieListWithSearch(String title, int year,
			String director, String first_name, String last_name);
	
}

