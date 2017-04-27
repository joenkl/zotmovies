
package com.spring.dao;

import java.util.List;

import com.spring.model.Movie;;

public interface MovieDao {

	public List<Movie> getMovieList(int page);

	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith);
	
	public int getTotalNumOfMovies();

	public List<Movie> getMovieListWithSearch(String title, int year, String director, String first_name,
			String last_name, String orderByColumn, String ascOrDesc, int page);
	
	public List<Movie> getMovieListWithStar(int starID);
	
	public List<Movie> getMovieListWithGenre(String genre);
	
	public Movie getMovieListWithID(int id);
	
}
