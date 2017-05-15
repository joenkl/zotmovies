package com.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Star {
	int id;
	String first_name;
	String last_name;
	Date dob; 
	String photo_url;
	String sDOB; 
	
	String stagename; 
	
	//for xml
	List<Movie> movies; 
	
	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
	
	public void addMovies(Movie movie){
		this.movies.add(movie);
	}

	public Star()
	{
		id = -1;
		movies = new ArrayList<Movie>();
	}
	
	public void setStagename(String name){
		stagename = name;
	}
	
	public String getStagename(){
		return stagename; 
	}

	
	public Star(int id, String first_name, String last_name, Date dob, String photo_url) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
		this.photo_url = photo_url;
	}
	
	public Star(int id, String first_name, String last_name, String dob, String photo_url) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.sDOB = dob;
		this.photo_url = photo_url;
	}
	@Override
	public String toString() {
		return "Star [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", dob=" + dob
				+ ", photo_url=" + photo_url + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	
	public String getsDOB() {
		return sDOB;
	}
	public void setsDOB(String sDOB) {
		this.sDOB = sDOB;
	}
	
}
