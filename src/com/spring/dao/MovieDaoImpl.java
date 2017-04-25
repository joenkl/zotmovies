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
	
	
	
	@Override
	public List<Movie> getMovieListWithGenre(String genre){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies, genres_in_movies, genres "
				+ "WHERE genres.name = '" + genre
				+ "' AND genres.id = genres_in_movies.genre_id AND genres_in_movies.movie_id = movies.id";
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
	public List<Movie> getMovieListWithStar(int starID){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies M JOIN stars_in_movies SM ON M.id = SM.movie_id WHERE SM.star_id = ?";
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
	             
	        }, starID);
		 
	        return listMovies;
	}
	
	@Override
	public Movie getMovieListWithID(int id){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies WHERE movies.id = ?";
		Movie movie = jdbcTemplate.queryForObject(sql, new RowMapper<Movie>() {
			 
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
             
        }, id);
		
		
		return movie;
	}
	
	
	@Override
	public List<Movie> getMovieListWithSearch(String title, int year,
			String director, String first_name, String last_name)
	{
		String sql = "SELECT * FROM movies M ";
		
		if(!first_name.isEmpty() || !last_name.isEmpty())
		{
			sql += "JOIN stars_in_movies SM ON M.id = SM.movie_id "
					+ "  WHERE SM.star_id in (SELECT id FROM stars WHERE ";
			
			if(!first_name.isEmpty())
			{
				sql += " first_name LIKE \"%" + first_name + "%\" ";
				
				if(!last_name.isEmpty())
				{
					sql += "AND ";
				}
				
				
			}
			
			if(!last_name.isEmpty())
			{
				sql += " last_name LIKE \"%" + last_name + "%\" ";
			}
			
			sql += " ) ";
			
			if (!title.isEmpty() || year != -1 || !director.isEmpty())
				sql += " AND ";
		}
		
		else // (!title.isEmpty() || year != -1 || !director.isEmpty())
			sql += " WHERE ";
		
		if(!title.isEmpty())
		{
			sql += " title LIKE \"%" + title + "%\" ";
			
			if(year != -1 || !director.isEmpty())
			{
				sql += " AND ";
			}
			
		}
		
		
		if(year != -1)
		{
			sql += "year = " + year + " ";  
			
			if(!director.isEmpty())
			{
				sql += " AND ";
			}
			
		}
		
		if(!director.isEmpty())
		{
			sql += " director LIKE \"%" + director + "%\" ";
		}
		
		
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
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
