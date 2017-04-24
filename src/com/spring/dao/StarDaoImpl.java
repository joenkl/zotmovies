package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Star;

public class StarDaoImpl implements StarDao {
	
	private DataSource dataSource;

	public StarDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<String> getStarsByMovieId(int movieId)
	{
		
		String sql = "SELECT first_name, last_name "
				+ "FROM stars  WHERE id in (SELECT star_id FROM stars_in_movies WHERE movie_id = ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<String> listStarName = jdbcTemplate.query(sql, new Object[] {movieId}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				String starName = (resultSet.getString(1)) + " " + resultSet.getString(2);
				return starName;
			}

		});

		return listStarName; 
	}
	
	
}
