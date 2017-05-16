package com.spring.model;

public class Genre_In_Movie {
	int movieId;
	int genreId;
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}
	public int getGenreId() {
		return genreId;
	}
	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}
	public Genre_In_Movie() {
		this.movieId = -1;
		this.genreId = -1;
	}
	
	

}
