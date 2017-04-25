
package com.spring.dao;

import java.util.List;

import com.spring.model.Movie;;

public interface MovieDao {

	public List<Movie> getMovieList();

	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith);
<<<<<<< HEAD
	
	public List<Movie> getMovieListWithGenre(String genre);
	
	public List<Movie> getMovieListWithStar(int starID);
	
	public Movie getMovieListWithID(int id);
	
	public List<Movie> getMovieListWithSearch(String title, int year,
			String director, String first_name, String last_name);
	
	
}
=======
>>>>>>> 4c819fc296c0fc76395d426c125c4840beba0396

	public List<Movie> getMovieListWithSearch(String title, int year, String director, String first_name,
			String last_name, String orderByColumn, String ascOrDesc);

}
