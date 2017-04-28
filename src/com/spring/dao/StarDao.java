package com.spring.dao;


import java.util.List;

import com.spring.model.Star;

public interface StarDao {
	public List<Star> getStarsByMovieId(int movieId);
}
