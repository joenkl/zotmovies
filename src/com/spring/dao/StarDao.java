package com.spring.dao;


import java.util.Date;
import java.util.List;

import com.spring.model.Star;

public interface StarDao {
	public List<Star> getStarsByMovieId(int movieId);
	public void addNewStar(int id, String first_name, String last_name,
						Date dob, String photo_url);
}
