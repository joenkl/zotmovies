package com.spring.dao;

import java.util.List;

import com.spring.model.Genre;

public interface GenreDao {
	public List<Genre> getGenreList();
	public List<String> getGenreListByMovieId(int movieId);
}
