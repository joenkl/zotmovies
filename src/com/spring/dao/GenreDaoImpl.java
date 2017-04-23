package com.spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spring.model.Genre;

public class GenreDaoImpl implements GenreDao{
	private DataSource dataSource;

	public GenreDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Genre> getGenreList(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM Genres";
		 List<Genre> listGenre = jdbcTemplate.query(sql, new RowMapper<Genre>() {
			 
	            @Override
	            public Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
	            	Genre genre = new Genre(resultSet.getInt(1),
							resultSet.getString(2)
							);
					return genre;
	            }
	             
	        });
		 
	        return listGenre;
	}

}
