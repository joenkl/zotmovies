package com.spring.dao;
import com.spring.model.Admin;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

public class AdminDaoImpl implements AdminDao {
	
	private DataSource dataSource;

	public AdminDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public Admin getAdminInfo(String email, String password) {
		try{
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			String sql = "select * from employees "
					+ "where email = ? and password = ?";
			
			Admin admin = (Admin) jdbcTemplate.queryForObject(sql, new RowMapper<Admin>() {
				@Override
				public Admin mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
					Admin admin = new Admin(resultSet.getString(1), resultSet.getString(2), 
									resultSet.getString(3));
					return admin;
				}
			}, email, password);
			
			return admin;
		}
		catch (EmptyResultDataAccessException e)
		{
			return null;
		}
	}
	
	@Override
	public String addMovieProcedure(String title, int year, String director, String banner_url, String trailer_url,
			String starFN, String starLN, Date starDob, String starPhotoURL, String genre) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("add_movie");

		Map<String, Object> in = new HashMap<String, Object>();
		in.put("new_movieTitle", title);
		in.put("new_year", year);
		in.put("new_director", director);
		in.put("new_banner_url", banner_url);
		in.put("new_trailer_url", trailer_url);
		in.put("new_star_FN", starFN);
		in.put("new_star_LN", starLN);
		in.put("new_star_dob", starDob);
		in.put("new_star_photo_url", starPhotoURL);
		in.put("new_genre", genre);

		SqlParameterSource sql = new MapSqlParameterSource(in);

		Map<String, Object> result = simpleJdbcCall.execute(sql);

		return result.get("msg").toString();
	}
	
	@Override
	public void addNewStar(String first_name, String last_name, Date dob, String photo_url) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql ="insert into stars (first_name, last_name, dob, photo_url) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, first_name, last_name, dob, photo_url);
		long endTime = System.nanoTime();

	}


}
