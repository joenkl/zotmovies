
package com.spring.dao;

import java.util.List;

import com.spring.model.Movie;;

public interface MovieDao {

	public List<Movie> getMovieList(int page);

	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith);
	
<<<<<<< HEAD
	public List<Movie> getMovieListWithGenre(String genre);
	
	public List<Movie> getMovieListWithStar(int starID);
	
	public Movie getMovieListWithID(int id);
	
	public List<Movie> getMovieListWithSearch(String title, int year,
			String director, String first_name, String last_name);
	
	
}
=======
	public int getTotalNumOfMovies();
>>>>>>> sorting-pagination

	public List<Movie> getMovieListWithSearch(String title, int year, String director, String first_name,
			String last_name, String orderByColumn, String ascOrDesc, int page);

}
