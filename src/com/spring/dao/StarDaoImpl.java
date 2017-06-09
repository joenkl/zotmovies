package com.spring.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Admin;
import com.spring.model.Star;

public class StarDaoImpl implements StarDao {
	
	@Autowired
	ServletContext context;
	
	private DataSource dataSource;

	public StarDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public List<Star> getStarsByMovieId(int movieId)
	{
		long startTime = System.nanoTime(); 
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
		
		long endTime = System.nanoTime();
		
		logRequest(Long.toString(endTime - startTime));

		return listStars; 
	}

	@Override
	public void addNewStar(String first_name, String last_name, Date dob, String photo_url) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql ="insert into stars (first_name, last_name, dob, photo_url) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, first_name, last_name, dob, photo_url);
		long endTime = System.nanoTime();

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
	
	public void logRequest(String data) {

		String path = context.getRealPath("/WEB-INF");

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			// String data = "new content";

			File file = new File(path, "TS_TJ_logs.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			// if file exists already, then append file
			fw = new FileWriter(file.getAbsolutePath(), true);
			bw = new BufferedWriter(fw);

			bw.write("TJ = " + data + "\n");

			System.out.println("Logged!");

		}

		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	
	
}
