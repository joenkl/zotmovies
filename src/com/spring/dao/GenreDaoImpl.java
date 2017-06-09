package com.spring.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Genre;

public class GenreDaoImpl implements GenreDao {
	
	@Autowired
	ServletContext context;
	
	private DataSource dataSource;

	public GenreDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Genre> getGenreList() {
		
 
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM genres";
		List<Genre> listGenre = jdbcTemplate.query(sql, new RowMapper<Genre>() {

			@Override
			public Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
				return genre;
			}

		});
		


		return listGenre;
	}

	@Override
	public List<Genre> getGenreListByMovieId(int movieID) {
		
		long startTime = System.nanoTime(); 
		String sql = "SELECT * FROM genres WHERE id in (SELECT genre_id FROM genres_in_movies WHERE movie_id = ?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		List<Genre> listGenre = jdbcTemplate.query(sql, new Object[] {movieID}, new RowMapper<Genre>() {

			@Override
			public Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Genre genre = new Genre(resultSet.getInt(1), resultSet.getString(2));
				return genre;
			}

		});
		
		long endTime = System.nanoTime();
		logRequest(Long.toString(endTime - startTime)); 

		return listGenre;
		
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
