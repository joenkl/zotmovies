package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Admin;
import com.spring.model.Star;

public class StarDaoImpl implements StarDao {
	
	private DataSource dataSource;

	public StarDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Star> getStarsByMovieId(int movieId)
	{
		
		String sql = "SELECT * "
				+ "FROM stars  WHERE id in (SELECT star_id FROM stars_in_movies WHERE movie_id = ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Star> listStars = jdbcTemplate.query(sql, new Object[] {movieId}, new RowMapper<Star>() {

			@Override
			public Star mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Star star = new Star(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getDate(4), resultSet.getString(5));
				return star;
			}

		});

		return listStars; 
	}

	@Override
	public void addNewStar(String first_name, String last_name, Date dob, String photo_url) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql ="insert into stars (first_name, last_name, dob, photo_url) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, first_name, last_name, dob, photo_url);
	}

	@Override
	public Boolean isStarExitByNames(String first_name, String last_name) {
		try{
			
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select * from stars "
					+ "where first_name = ? and last_name = ?";
			
			Star star = (Star) jdbcTemplate.queryForObject(sql, new RowMapper<Star>() {
				@Override
				public Star mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					Star star = new Star(resultSet.getInt(1), resultSet.getString(2), 
									resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
					return star;
				}
			}, first_name, last_name);
			
			return true;
		}
		catch (EmptyResultDataAccessException e)
		{
			return false;
		}
	}
	
	
	
}
