package com.spring.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Movie;

public class MovieRowMapper implements RowMapper<Object> {
	
	public Movie mapRow(ResultSet resultSet, int rowNum) throws SQLException
	{
		Movie movie = new Movie(resultSet.getInt(1),
				resultSet.getString(2),
				resultSet.getInt(3),
				resultSet.getString(4),
				resultSet.getString(5),
				resultSet.getString(6)
				);
		return movie;
		
		
	}

}