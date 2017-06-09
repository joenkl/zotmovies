
package com.spring.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.swing.plaf.synth.SynthSeparatorUI;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.spring.model.Movie;

public class MovieDaoImpl implements MovieDao {

	@Autowired
	ServletContext context;

	private DataSource dataSource;

	public MovieDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public List<Movie> getMovieList(int page, int n) {
		long startTime = System.nanoTime();
		//////////////////////////////////////////
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String preparedStmt = "SELECT * FROM movies LIMIT ? OFFSET ?";
		// String sql = "SELECT * FROM movies LIMIT " + n + " OFFSET " + ((page
		// - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(preparedStmt, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setInt(1, n);
				stmt.setInt(2, (page - 1) * n);

			}

		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});
		//////////////////////

		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return listMovies;
	}

	@Override
	public List<Movie> getMovieListWhereTitlesStartWith(String StartWith, String orderByColumn, String ascOrDesc,
			int page, int n) {

		long startTime = System.nanoTime();
		//////////////////////////////
		String order_style = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// String sql = "SELECT * FROM movies WHERE movies.title LIKE '" +
		// StartWith + "%'";

		String preparedStmt = "SELECT * FROM movies WHERE title LIKE ? " + " ORDER BY " + orderByColumn + " "
				+ order_style + " LIMIT " + n + " OFFSET " + ((page - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(preparedStmt, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement stmt) throws SQLException {
				stmt.setString(1, StartWith + "%");
			}

		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});
		//////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

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

		long startTime = System.nanoTime();
		/////////////////////
		String sql = "SELECT * FROM movies M ";

		String title_arg = "";
		String year_arg = "";
		String director_arg = "";
		String fn_arg = "";
		String ln_arg = "";

		if (!first_name.isEmpty() || !last_name.isEmpty()) {
			sql += "JOIN stars_in_movies SM ON M.id = SM.movie_id "
					+ "  WHERE SM.star_id in (SELECT id FROM stars WHERE ";

			if (!first_name.isEmpty()) {
				sql += " first_name LIKE ?";
				fn_arg += "%" + first_name + "%";

				if (!last_name.isEmpty()) {
					sql += "AND ";
				}

			}

			if (!last_name.isEmpty()) {
				sql += " last_name LIKE ?";
				ln_arg += "%" + last_name + "%";
			}

			sql += " ) ";

			if (!title.isEmpty() || year != -1 || !director.isEmpty())
				sql += " AND ";
		}

		else // (!title.isEmpty() || year != -1 || !director.isEmpty())
			sql += " WHERE ";

		if (!title.isEmpty()) {
			sql += " title LIKE ?";
			title_arg += "%" + title + "%";

			if (year != -1 || !director.isEmpty()) {
				sql += " AND ";
			}

		}

		if (year != -1) {
			sql += "year = ?";
			year_arg += Integer.toString(year);

			if (!director.isEmpty()) {
				sql += " AND ";
			}

		}

		if (!director.isEmpty()) {
			sql += " director LIKE ?";
			director_arg += "%" + director + "%";
		}

		ascOrDesc = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		sql += "\n ORDER BY " + orderByColumn + " " + ascOrDesc + " LIMIT " + n + " OFFSET " + ((page - 1) * n);

		String _fn = fn_arg;
		String _ln = ln_arg;
		String _year = year_arg;
		String _title = title_arg;
		String _director = director_arg;

		// System.out.println(_fn + " " + _ln + " " + _year + " " + _title + " "
		// + _director);
		// System.out.println(sql);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Movie> listMovies = jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				// fn => ln => title => year => director

				int i = 1;

				if (!_fn.isEmpty()) {
					arg0.setString(i, _fn);
					i++;
				}
				if (!_ln.isEmpty()) {
					arg0.setString(i, _ln);
					i++;
				}
				if (!_title.isEmpty()) {
					// System.out.println(_title);
					// System.out.println(i);
					arg0.setString(i, _title);
					i++;
				}
				if (!_year.isEmpty()) {
					arg0.setInt(i, year);
					i++;
				}
				if (!_director.isEmpty()) {
					arg0.setString(i, _director);
					i++;
				}

			}
		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});
		//////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return listMovies;

	}

	@Override
	public List<Movie> getMovieListWithGenre(String genre, String orderByColumn, String ascOrDesc, int page, int n) {
		long startTime = System.nanoTime();
		///////////////////////////
		ascOrDesc = (ascOrDesc.equals("a-z") || ascOrDesc.equals("1-9")) ? "ASC" : "DESC";

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// String sql = "select * from movies M " + "join genres_in_movies GM on
		// M.id = GM.movie_id "
		// + "where GM.genre_id in (select id from genres where name='" + genre
		// + "')" + " ORDER BY "
		// + orderByColumn + " " + ascOrDesc + " LIMIT " + n + " OFFSET " +
		// ((page - 1) * n);

		String preparedStmt = "select * from movies M " + "join genres_in_movies GM on M.id = GM.movie_id "
				+ "where GM.genre_id in (select id from genres where name= ? )" + " ORDER BY " + orderByColumn + " "
				+ ascOrDesc + " LIMIT " + n + " OFFSET " + ((page - 1) * n);
		List<Movie> listMovies = jdbcTemplate.query(preparedStmt, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement arg0) throws SQLException {
				arg0.setString(1, genre);

			}

		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});
		////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

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
	public List<Movie> fuzzy_search(String title) {
		long startTime = System.nanoTime();
		/////////////////////////////////////////////////
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String prepared_stmt = "SELECT * FROM movies WHERE MATCH(title) AGAINST(? IN BOOLEAN MODE);";
		String sql = generateStmt(title);
		List<Movie> listMovies = jdbcTemplate.query(prepared_stmt, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement preparedstmt) throws SQLException {
				// System.out.println(sql);
				preparedstmt.setString(1, sql);

			}

		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		/////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return listMovies;
	}

	@Override
	public List<String> api_search(String title) {
		long startTime = System.nanoTime();
		/////////////////////////////
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String prepared_stmt = "SELECT title FROM movies WHERE MATCH(title) AGAINST(? IN BOOLEAN MODE);";
		String sql = generateStmt(title);
		List<String> listMovies = jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement preparedstmt) throws SQLException {
				preparedstmt.setString(1, sql);

			}

		}, new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				String movieTitle = resultSet.getString(1);
				return movieTitle;
			}

		});

		/////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return listMovies;

	}

	@Override
	public List<Movie> getAllMovie() {

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
	public List<String> getAllMovieTitle() {

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

	@Override
	public List<Movie> fuzzy_search_1(String title) {

		String[] words = title.split(" ");
		String arguments = "";

		for (String word : words) {
			arguments += "+" + word.trim() + "* ";
		}

		final String argument = arguments;

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies WHERE MATCH(title) AGAINST(? IN BOOLEAN MODE)";

		List<Movie> listMovies = jdbcTemplate.query(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(java.sql.PreparedStatement preparedStmt) throws SQLException {
				preparedStmt.setString(1, argument);

			}

		}, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		return listMovies;

	}

	public static String generateStmt(String title) {

		String[] words = title.split(" ");

		String query = "";

		for (String word : words) {

			if (word.length() >= 3) {
				if (word.contains("+") || word.contains("-") || word.contains("*") || word.contains("/")
						|| word.contains(">") || word.contains("<") || word.contains("%") || word.contains(")")
						|| word.contains("(") || word.contains("~") || word.contains("@") || word.contains("\"")) {

					word.replace("+", "");
					word.replace("-", "");
					word.replace("*", "");
					word.replace("/", "");
					word.replace(">", "");
					word.replace("<", "");
					word.replace("%", "");
					word.replace(")", "");
					word.replace("(", "");
					word.replace("~", "");
					word.replace("@", "");
					word.replace("\"", "");

					query += "+" + word + "* ";

				}
				if (word.contains("'") || word.contains("\b") || word.contains("\n") || word.contains("\t")
						|| word.contains("\\")

				) {
					word = word.replace("'", "\\'");
					word = word.replace("\b", " ");
					word = word.replace("\n", " ");
					word = word.replace("\t", " ");
					word = word.replace("\\", "\\\\");

					query += "+" + word + "* ";
				}

				else
					query += "+" + word + "* ";
			}

		}

		return query;

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
