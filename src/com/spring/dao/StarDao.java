package com.spring.dao;


import java.util.List;

import com.spring.model.Star;

public interface StarDao {
	public List<String> getStarsByMovieId(int movieId);
}
