package com.spring.model;

import java.util.Date;

public class Star {
	int id;
	String first_name;
	String last_name;
	Date dob; 
	String photo_url;
	
	public Star(int id, String first_name, String last_name, Date dob, String photo_url) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.dob = dob;
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
	
}
