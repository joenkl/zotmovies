package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Movie;


public class MovieDaoImpl implements MovieDao {
	private DataSource dataSource;

	public MovieDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Movie> getMovieList(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies";
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {
			 
	            @Override
	            public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
	            	Movie movie = new Movie(resultSet.getInt(1),
							resultSet.getString(2),
							resultSet.getInt(3),
							resultSet.getString(4),
							resultSet.getString(5),
							resultSet.getString(6)
							);
					return movie;
	            }
	             
	        });
		 
	        return listMovies;
	}

	@Override
	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies WHERE movies.title LIKE '" + StartWith + "%'";
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {
			 
	            @Override
	            public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
	            	Movie movie = new Movie(resultSet.getInt(1),
							resultSet.getString(2),
							resultSet.getInt(3),
							resultSet.getString(4),
							resultSet.getString(5),
							resultSet.getString(6)
							);
					return movie;
	            }
	             
	        });
		 
	        return listMovies;
	}
	
}
