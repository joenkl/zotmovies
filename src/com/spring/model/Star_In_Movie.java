package com.spring.model;

public class Star_In_Movie {
	
	int starId;
	int movieId;
	
	public Star_In_Movie(){
		starId = -1;
		movieId= -1;
	}
	
	public int getStarId() {
		return starId;
	}

	public void setStarId(int starId) {
		this.starId = starId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	
	

}
