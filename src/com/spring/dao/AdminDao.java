package com.spring.dao;

import java.sql.Date;

import com.spring.model.Admin;
public interface AdminDao {
	public Admin getAdminInfo (String email, String password);
	
	public String addMovieProcedure(String title, int year, String director, String banner_url, String trailer_url,
			String starFN, String starLN, Date starDob, String starPhotoURL, String genre);
	
	public void addNewStar(String first_name, String last_name, Date dob, String photo_url);
}
