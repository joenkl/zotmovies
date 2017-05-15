package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.directory.InitialDirContext;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.InputSource;

import com.spring.config.AppConfig;
import com.spring.config.MvcConfiguration;
import com.spring.dao.MovieDao;
import com.spring.model.Genre;
import com.spring.model.Movie;
import com.spring.model.Star;

import java.sql.SQLException;
import java.text.ParseException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.spring.model.Genre;


public class MovieParser extends DefaultHandler {
	// movies from database with key = (Director, Year, Title)
	private Hashtable<ImmutableTriple<String, Integer, String>, Movie> moviedb;

	// moviesFidXml is movies found from parsing xml and key = fid
	private Hashtable<String, Movie> moviesFidXml;

	// moviexml from parsing xml where key = (Director, Year, Title)
	private Hashtable<ImmutableTriple<String, Integer, String>, Movie> moviexml;

	// to read value of each tag
	private String tempVal;

	// to hold value of a movie after parsing
	private Movie tempMovie;

	// dataSource holds connection to database
	private DataSource dataSource;

	// boolean to indicate whether xml provides genre
	boolean hasCat;

	// Hashtable of new genre to add:
	Hashtable<String, Genre> new_genres;

	// Hashtable of genre in db:
	Hashtable<String, Genre> genres_in_db;
	
	//Connection of autocommit:
	Connection con; 

	/*
	 * constructor for MovieParser() for: get data source to connect to db
	 * initialize moviesFidXml initialize moviedb to movies in database
	 * initialize moviexml
	 */
	public MovieParser() throws NamingException, SQLException {
		System.out.println("Star logging for mains243.xml");

		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");
		
		con = dataSource.getConnection();
		con.setAutoCommit(false);

		moviesFidXml = new Hashtable<String, Movie>();

		moviedb = getMoviedb();
		// System.out.println(movieDao.getMovieHashtable());

		moviexml = new Hashtable<ImmutableTriple<String, Integer, String>, Movie>();

		hasCat = false;

		new_genres = new Hashtable<String, Genre>();

		genres_in_db = getGenresInDb();
		
		

	}

	// star parsing document here...
	private void parseDocument() {

		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			// get the file:
			File xmlFile = new File("WebContent/resources/xml/mains243.xml");

			// make sure the encoding is ISO-8859-1
			InputStream inputStream = new FileInputStream(xmlFile);
			Reader reader = new InputStreamReader(inputStream, "ISO-8859-1");

			InputSource inputSource = new InputSource(reader);
			inputSource.setEncoding("ISO-8859-1");

			// parse the file and also register this class for call backs
			sp.parse(inputSource, this);

		} catch (SAXException se) {
			se.printStackTrace();
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	// Event Handlers
	// if encounter <film> tag, then a new movie will be created by default
	// constructor of Movie()
	/*
	 * default constructor of Movie: id = -1; year = 0000; listOfStars = new
	 * ArrayList<Star>(); directorId = -1; fid = "N/A"; listOfGenres = new
	 * ArrayList<Genre>();
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("film")) {
			tempMovie = new Movie();
			hasCat = false;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		// when encounter </film>, then update all the needed fields for a movie
		if (qName.equalsIgnoreCase("film")) {

			// validate data:
			boolean isValidFid = !tempMovie.getFid().equals("N/A");
			boolean isValidYear = tempMovie.getYear() != 0000;

			// key is <director, year, title> => if string, then all are lower
			// case
			ImmutableTriple<String, Integer, String> key = new ImmutableTriple<String, Integer, String>(
					tempMovie.getDirector().toLowerCase(), tempMovie.getYear(), tempMovie.getTitle().toLowerCase());

			// duplicate hp when moviesFidXml already have that fid
			// or when moviedb already have that film
			// or when moviexml already have that film
			boolean isDuplicate = moviesFidXml.containsKey(tempMovie.getFid()) || moviedb.containsKey(key)
					|| moviexml.containsKey(key);

			// logging message to console
			if (!isValidFid) {
				System.out.println("Encounter invalid fid format " + tempMovie.getFid());
			}

			if (isDuplicate) {
				System.out.println("Encounter duplicate movie where title=" + tempMovie.getTitle() + " year = "
						+ tempMovie.getYear() + " director = " + tempMovie.getDirector());

			}

			// insert those valid
			if ((isValidFid && isValidYear && !isDuplicate)) {

				moviesFidXml.put(tempMovie.getFid(), tempMovie);
				moviexml.put(key, tempMovie);

			}

		} else if (qName.equalsIgnoreCase("fid")) {
			tempMovie.setFid(tempVal);

		} else if (qName.equalsIgnoreCase("t")) {
			tempMovie.setTitle(tempVal);

		} else if (qName.equalsIgnoreCase("year")) {
			if (tryParseInt(tempVal))
				tempMovie.setYear(Integer.parseInt(tempVal));
			else {
				// invalid year format
				tempMovie.setYear(0000);
				System.out.println("Invalid year format " + tempVal + " changed to format 0000");

			}
		} else if (qName.equalsIgnoreCase("dirn")) {
			tempMovie.setDirector(tempVal);
		} else if (qName.equalsIgnoreCase("cat")) {

			this.hasCat = true;

			String[] parts = tempVal.split(" ");

			for (int i = 0; i < parts.length; i++) {
				Genre genre = new Genre();
				genre.setName(parts[i].toLowerCase());
				//
				// // add genre:
				// // check whether genre already exist in that movie:
				if (!genre.getName().equals("N/A") && !genre.getName().isEmpty() && !this.new_genres.containsKey(genre.getName().toLowerCase())
						&& !this.genres_in_db.containsKey(genre.getName().toLowerCase())) {
					this.new_genres.put(genre.getName().toLowerCase(), genre);
				}

			}

		} else if (!hasCat && qName.equalsIgnoreCase("cattext")) {
			String[] parts = tempVal.split(" ");

			for (int i = 0; i < parts.length; i++) {
				Genre genre = new Genre();
				genre.setName(parts[i].toLowerCase());
				//
				// // add genre:
				// // check whether genre already exist in that movie:
				if (!genre.getName().equals("N/A") && !genre.getName().isEmpty() && !this.new_genres.containsKey(genre.getName().toLowerCase())
						&& !this.genres_in_db.containsKey(genre.getName().toLowerCase())) {
					this.new_genres.put(genre.getName().toLowerCase(), genre);
				}
			}
		}

	}

	public void PopulateGenre() throws SQLException {
		List<String> genreList = new ArrayList<String>();

		for(Entry<String, Genre> entry : this.new_genres.entrySet())
		{
			genreList.add(entry.getValue().getName());
			
		}
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(new SingleConnectionDataSource(con, false));
		
		
		
		String sql ="insert into genres (name) values (?)";
	
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter(){

			@Override
			public int getBatchSize() {
				return genreList.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, genreList.get(i));
			}

		});
		
	
		//dataSource.getConnection().commit();
		
	}

	public void run() throws SQLException {
		parseDocument();
		PopulateGenre(); 
		// printData();
		// testMoviedb();
		// System.out.println(moviedb);

		// System.out.println("Total valid movies parsing from this xml: " +
		// this.moviesFidXml.size());
		System.out.println(this.new_genres);
	}

	public static void main(String[] args) throws NamingException, SQLException {
		MovieParser spe = new MovieParser();
		spe.run();
	}

	private boolean tryParseInt(String number) {
		try {
			int temp = Integer.parseInt(number);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	public Hashtable<String, Movie> getMoviesHashTable() {
		return moviesFidXml;
	}

	public Hashtable<ImmutableTriple<String, Integer, String>, Movie> getMoviedbHashTable() {
		return this.moviedb;
	}

	public Hashtable<String, Genre> getGenresInDb() {
		Hashtable<String, Genre> hashtable = new Hashtable<String, Genre>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select * from genres";

		List<Genre> listGenres = jdbcTemplate.query(query, new RowMapper<Genre>() {

			@Override
			public Genre mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

				Genre genre = new Genre();
				genre.setId(resultSet.getInt(1));
				genre.setName(resultSet.getString(2));
				return genre;
			}

		});

		for (Genre g : listGenres) {
			hashtable.put(g.getName().toLowerCase(), g);
		}

		// System.out.println(hashtable);
		return hashtable;

	}

	/*
	 * select genre_id, g.name, movie_id, m.director, m.year, m.title from
	 * genres_in_movies gm join genres g on gm.genre_id = g.id join movies m on
	 * gm.movie_id = m.id;
	 */
	public Hashtable<ImmutableTriple<String, Integer, String>, Movie> getMoviedb() throws NamingException {

		Hashtable<ImmutableTriple<String, Integer, String>, Movie> hashtable = new Hashtable<ImmutableTriple<String, Integer, String>, Movie>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select genre_id, g.name, movie_id, m.director, m.year, m.title " + "from genres_in_movies gm "
				+ "join genres g on gm.genre_id = g.id " + "join movies m on gm.movie_id = m.id; ";

		List<Movie> listMovies = jdbcTemplate.query(query, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie();
				Genre genre = new Genre();

				genre.setId(resultSet.getInt(1));
				genre.setName(resultSet.getString(2));

				movie.setId(resultSet.getInt(3));
				movie.setDirector(resultSet.getString(4));
				movie.setYear(resultSet.getInt(5));
				movie.setTitle(resultSet.getString(6));
				movie.addGenre(genre);
				return movie;
			}

		});

		for (Movie movie : listMovies) {
			ImmutableTriple<String, Integer, String> key = new ImmutableTriple<String, Integer, String>(
					movie.getDirector().toLowerCase(), movie.getYear(), movie.getTitle().toLowerCase());
			boolean isEmpty = hashtable.isEmpty();
			boolean isDuplicate = hashtable.containsKey(key);

			if (isEmpty || !isDuplicate) {
				hashtable.put(key, movie);
			}

			if (isDuplicate) {
				List<Genre> tempGenre = hashtable.get(key).getGenres();
				List<Genre> currentGenre = movie.getGenres();

				// combine list of genres:
				tempGenre.addAll(currentGenre);
				Movie m = hashtable.get(key);
				m.setGenres(tempGenre);

				// update accordingly
				hashtable.remove(key);
				hashtable.put(key, m);
			}
		}

		// System.out.println(hashtable);
		return hashtable;

	}

}
