package com.spring.model;

import java.util.ArrayList;
import java.util.List;

public class Genre {
	int id;
	String name;

	List<Movie> listMovies;
	public Genre(){
		this.id = -1;
		listMovies = new ArrayList<Movie>();
		name ="N/A";
		
	}
	
	public List<Movie> getMovies(){
		return this.listMovies;
	}
	
	public void addMovie(Movie e){
		this.listMovies.add(e);
	}
	
	public void setMovies(List<Movie> lm){
		this.listMovies = lm; 
	}
	
	public Genre(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
