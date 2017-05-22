
package com.spring.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.spring.model.Movie;

public class MovieDaoImpl implements MovieDao {
	private DataSource dataSource;

	public MovieDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Movie> getMovieList(int page, int n) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies LIMIT " + n + " OFFSET " + ((page - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;
	}

	@Override
	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith, String orderByColumn, String ascOrDesc,
			int page, int n) {
		ascOrDesc = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies WHERE movies.title LIKE '" + StartWith + "%'" + "ORDER BY " + orderByColumn
				+ " " + ascOrDesc + " LIMIT " + n + " OFFSET " + ((page - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;
	}

	@Override
	public int getTotalNumOfMovies() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT count(*) FROM movies";
		int total = jdbcTemplate.queryForObject(sql, Integer.class);

		return total;

	}

	@Override
	// by default the function will return the search result order by title asc
	public List<Movie> getMovieListWithSearch(String title, int year, String director, String first_name,
			String last_name, String orderByColumn, String ascOrDesc, int page, int n) {
		String sql = "SELECT * FROM movies M ";

		if (!first_name.isEmpty() || !last_name.isEmpty()) {
			sql += "JOIN stars_in_movies SM ON M.id = SM.movie_id "
					+ "  WHERE SM.star_id in (SELECT id FROM stars WHERE ";

			if (!first_name.isEmpty()) {
				sql += " first_name LIKE \"%" + first_name + "%\" ";

				if (!last_name.isEmpty()) {
					sql += "AND ";
				}

			}

			if (!last_name.isEmpty()) {
				sql += " last_name LIKE \"%" + last_name + "%\" ";
			}

			sql += " ) ";

			if (!title.isEmpty() || year != -1 || !director.isEmpty())
				sql += " AND ";
		}

		else // (!title.isEmpty() || year != -1 || !director.isEmpty())
			sql += " WHERE ";

		if (!title.isEmpty()) {
			sql += " title LIKE \"%" + title + "%\" ";

			if (year != -1 || !director.isEmpty()) {
				sql += " AND ";
			}

		}

		if (year != -1) {
			sql += "year = " + year + " ";

			if (!director.isEmpty()) {
				sql += " AND ";
			}

		}

		if (!director.isEmpty()) {
			sql += " director LIKE \"%" + director + "%\" ";
		}

		ascOrDesc = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		sql += "\n ORDER BY " + orderByColumn + " " + ascOrDesc + " LIMIT " + n + " OFFSET " + ((page - 1) * n);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;

	}

	@Override
	public List<Movie> getMovieListWithGenre(String genre, String orderByColumn, String ascOrDesc, int page, int n) {

		ascOrDesc = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "select * from movies M " + "join genres_in_movies GM on M.id = GM.movie_id "
				+ "where GM.genre_id in (select id from genres where name='" + genre + "')" + " ORDER BY "
				+ orderByColumn + " " + ascOrDesc + " LIMIT " + n + " OFFSET " + ((page - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;
	}

	@Override
	public List<Movie> getMovieListWithStar(int starID) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies M JOIN stars_in_movies SM ON M.id = SM.movie_id WHERE SM.star_id = ?";
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		}, starID);

		return listMovies;
	}

	@Override
	public Movie getMovieListWithID(int id) {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies WHERE movies.id = ?";
		Movie movie = jdbcTemplate.queryForObject(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		}, id);

		return movie;
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
	public List<Movie> fuzzy_search(String query) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = query; 
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;
	}
	
	@Override
	public List<Movie> getAllMovie(){
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies";
		List<Movie> listMovies = jdbcTemplate.query(sql, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;
		
	}
	
	
	@Override
	public List<String> getAllMovieTitle(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT title FROM movies";
		List<String> listMovies = jdbcTemplate.query(sql, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				String title = resultSet.getString(1);
				return title; 
			}

		});

		return listMovies;
		
		
	}
}
