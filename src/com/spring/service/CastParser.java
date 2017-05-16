package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.spring.model.Genre;
import com.spring.model.Movie;
import com.spring.model.Star;
import com.spring.model.Star_In_Movie;

import javafx.util.Pair;

//TODO: fid doesnt exist then pass that cast 
public class CastParser extends DefaultHandler {
	// hash table of movies where key = fid
	private Hashtable<String, Movie> movies;

	private String tempVal;

	// hash table of new casts:
	private Hashtable<String, List<Movie>> star_in_movie_xml;

	private Movie tempMovie;

	private MovieParser mp;

	private DataSource dataSource;

	private Hashtable<String, Star> starxml;
	// hash table of star from db from actorparser
	private Hashtable<String, Star> stardb;

	// hash table of star_in_movie
	private Hashtable<String, List<Movie>> star_in_movie_db;

	// hash table
	private Hashtable<ImmutableTriple<String, Integer, String>, Movie> movieAfterPopulated;

	// hash table
	private Hashtable<String, Star> starAfterPopulated;

	//
	List<Star_In_Movie> star_in_movie_toAdd;

	int movie_id;
	int star_id;

	public Hashtable<String, Star> get_star_in_db_after_populated() {
		Hashtable<String, Star> hashtable = new Hashtable<String, Star>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select * from stars";

		List<Star> listS = jdbcTemplate.query(query, new RowMapper<Star>() {

			@Override
			public Star mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

				Star star = new Star();
				star.setId(resultSet.getInt(1));
				star.setFirst_name(resultSet.getString(2));
				star.setLast_name(resultSet.getString(3));

				return star;
			}

		});

		for (Star star : listS) {
			hashtable.put(star.getFirst_name() + " " + star.getLast_name(), star);
		}

		// System.out.println(hashtable);
		return hashtable;
	}

	public Hashtable<Pair<Integer, Integer>, Integer> getStar_in_Movie_in_db() {
		Hashtable<Pair<Integer, Integer>, Integer> hashtable = new Hashtable<Pair<Integer, Integer>, Integer>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select * from stars_in_movies";

		List<Star_In_Movie> listS = jdbcTemplate.query(query, new RowMapper<Star_In_Movie>() {

			@Override
			public Star_In_Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

				Star_In_Movie sim = new Star_In_Movie();
				sim.setStarId(resultSet.getInt(1));
				sim.setMovieId(resultSet.getInt(2));

				return sim;
			}

		});

		for (Star_In_Movie sim : listS) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>(sim.getStarId(), sim.getMovieId());
			hashtable.put(key, 1);
		}

		// System.out.println(hashtable);
		return hashtable;
	}

	public CastParser() throws NamingException, IOException, SQLException, ParseException {
		System.out.println("CASTS124.XML PARSING");
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");

	}

	private void parseDocument() {

		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			// get the file:
			File xmlFile = new File("WebContent/resources/xml/casts124.xml");

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

	/**
	 * Iterate through the list and print the contents
	 */
	private void printData() {

		System.out.println("No of Movies '" + movies.size() + "'.");

		//
		// for (Entry<String, Movie> entry : movies.entrySet()) {
		// String key = entry.getKey();
		// Movie value = entry.getValue();
		//
		// System.out.println ("Key: " + key + " Value: " + value);
		// }
	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("f")) {
			tempMovie = new Movie();
			star_id = -1;
			movie_id = -1;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("f")) {
			if (!tempVal.isEmpty()) {
				if (movies.containsKey(tempVal)) {
					// look up movie
					tempMovie = movies.get(tempVal);

					// get movieid:
					ImmutableTriple<String, Integer, String> key = new ImmutableTriple<String, Integer, String>(
							tempMovie.getDirector().toLowerCase(), tempMovie.getYear(),
							tempMovie.getTitle().toLowerCase());
					
					if(this.movieAfterPopulated.containsKey(key))
						movie_id = this.movieAfterPopulated.get(key).getId();

				}

				else {
					System.out.println("fid in casts124.xml doesn't exist in mains243.xml");
				}
			}

			else {
				System.out.println("fid doesn't exist in casts124.xml");
			}
		}

		else if (qName.equals("a")) {
			if (!(tempMovie.getFid().equals("N/A"))) {

				// movie has unknown actors would be removed
				if (tempVal.equals("s a")) {
					System.out.println("Encounter unknown actor for " + tempMovie + " in casts124.xml");
				}

				else {

					if (this.starAfterPopulated.containsKey(tempVal.toLowerCase())) {
						star_id = starAfterPopulated.get(tempVal.toLowerCase()).getId();

					}

					if (star_id != -1 && movie_id != -1) {
						Star_In_Movie sim = new Star_In_Movie();
						sim.setStarId(star_id);
						sim.setMovieId(movie_id);
						this.star_in_movie_toAdd.add(sim);
					}

				}

			}

		}

	}

	public void run() throws NamingException, SQLException, IOException, ParseException {

		mp = new MovieParser();
		mp.run();
		movies = mp.getMoviesHashTable(); // with key = fid
		movieAfterPopulated = mp.getMovieDbAfterPopulated(); // with key =
																// director,
																// year, title

		ActorParser ap = new ActorParser();
		ap.run();

		starAfterPopulated = get_star_in_db_after_populated(); // with key =
																// stagename

		parseDocument();

		// public Hashtable<Pair<Integer, Integer>, Integer>
		// getStar_in_Movie_in_db()
		Hashtable<Pair<Integer, Integer>, Integer> lookUpStar_in_Movie = getStar_in_Movie_in_db();

		// List<Star_In_Movie> star_in_movie_toAdd;

		for (Star_In_Movie sim : this.star_in_movie_toAdd) {
			Pair<Integer, Integer> key = new Pair<Integer, Integer>(sim.getStarId(), sim.getMovieId());
			if (lookUpStar_in_Movie.containsKey(key)) {
				this.star_in_movie_toAdd.remove(sim);
			}
		}
		
		populateStarInMovie(); 

	}

	public void populateStarInMovie() {

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into stars_in_movies (genre_id, movie_id) values (?, ?)";

		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

			@Override
			public int getBatchSize() {
				return star_in_movie_toAdd.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setInt(1, star_in_movie_toAdd.get(i).getStarId());
				ps.setInt(2, star_in_movie_toAdd.get(i).getMovieId());
			}
		});
	}
	

	public static void main(String[] args) throws NamingException, IOException, SQLException, ParseException {
		CastParser spe = new CastParser();
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
		return movies;
	}

	public Hashtable<String, List<Movie>> getStardb() throws NamingException {

		Hashtable<String, List<Movie>> hashtable = new Hashtable<String, List<Movie>>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "select star_id, first_name, last_name, dob, movie_id, title, year, director from stars_in_movies sm "
				+ "JOIN stars s ON sm.star_id = s.id " + "JOIN movies m ON m.id = sm.movie_id";

		List<Star> listStars = jdbcTemplate.query(query, new RowMapper<Star>() {

			@Override
			public Star mapRow(ResultSet resultSet, int rowNumber) throws SQLException {

				Star star = new Star();
				star.setId(resultSet.getInt(1));
				star.setFirst_name(resultSet.getString(2));
				star.setLast_name(resultSet.getString(3));
				star.setsDOB(Integer.toString(resultSet.getDate(4).getYear()));

				Movie movie = new Movie();
				movie.setId(resultSet.getInt(5));
				movie.setTitle(resultSet.getString(6));
				movie.setYear(resultSet.getInt(7));
				movie.setDirector(resultSet.getString(8));

				star.addMovies(movie);
				return star;
			}

		});

		for (Star star : listStars) {
			String key = star.getFirst_name() + " " + star.getLast_name();

			boolean isEmpty = hashtable.isEmpty();
			boolean isDuplicate = hashtable.containsKey(key.toLowerCase());

			if (isEmpty || !isDuplicate) {
				hashtable.put(key, star.getMovies());
			}

			if (isDuplicate) {
				List<Movie> temp = hashtable.get(key.toLowerCase());
				List<Movie> current = star.getMovies();
				// combine movie list
				temp.addAll(current);
				hashtable.remove(key);
				hashtable.put(key, temp);

			}

		}

		return hashtable;

	}

}
