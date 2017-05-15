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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
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
import com.spring.model.Movie;

public class MovieParser extends DefaultHandler {
	// movies from database
	private Hashtable<ImmutableTriple<String, Integer, String>, Movie> moviedb;

	// hash table of movie in xml with fid 
	private Hashtable<String, Movie> movies;

	// hash table of movie with <director, year, title> as key:
	private Hashtable<ImmutableTriple<String, Integer, String>, Movie> moviexml;
	private String tempVal;

	private Movie tempMovie;

//	@Autowired
//	private MovieDao moviedao;

	@Resource(name = "jdbc/moviedb")
	private DataSource dataSource;

	public MovieParser() throws NamingException {

		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");

		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(MvcConfiguration.class);
		// moviedao = (MovieDao) context.getBean("MovieDao");
		//
		movies = new Hashtable<String, Movie>();

		moviedb = getMoviedb();
		// System.out.println(movieDao.getMovieHashtable());

		moviexml = new Hashtable<ImmutableTriple<String, Integer, String>, Movie>();

	}

	private void testMoviedb() {
		System.out.println(moviedb.size());
	}

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

	/**
	 * Iterate through the list and print the contents
	 */
	private void printData() {

		System.out.println("No of Movies '" + movies.size() + "'.");

		// Iterator<Movie> it = myMovies.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next().toString());
		// }
	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("film")) {
			tempMovie = new Movie();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("film")) {

			//validate data: 
			boolean isEmpty = movies.isEmpty() || moviexml.isEmpty();
			boolean isValidFid = !tempMovie.getFid().equals("N/A");
			boolean isValidYear = tempMovie.getYear() != 0000;
			
			ImmutableTriple<String, Integer, String> key = new ImmutableTriple<String, Integer, String>(tempMovie.getDirector(), tempMovie.getYear(), tempMovie.getTitle()); 
			
			
			boolean isDuplicate = movies.containsKey(tempMovie.getFid()) || moviedb.containsKey(key) || moviexml.containsKey(key);
			
			if(!isValidFid){
				System.out.println("Encounter invalid fid format " + tempMovie.getFid());
			}
			
			if(isDuplicate){
				System.out.println("Encounter duplicate movie where title=" + tempMovie.getTitle() + " year = " + tempMovie.getYear() + " director = " + tempMovie.getDirector());
				
			}
			
			// insert those valid
			if (isEmpty || (isValidFid && isValidYear && !isDuplicate)) {
				
				movies.put(tempMovie.getFid(), tempMovie);
				moviexml.put(key, tempMovie);
				
			} 
			//else {
//				System.out.println("Cannot insert the following into database: ");
//				System.out.println(tempMovie);
//			}

		} else if (qName.equalsIgnoreCase("fid")) {
			tempMovie.setFid(tempVal);
			
		} else if (qName.equalsIgnoreCase("t")) {
			tempMovie.setTitle(tempVal);
			
		} else if (qName.equalsIgnoreCase("year")) {
			if (tryParseInt(tempVal))
				tempMovie.setYear(Integer.parseInt(tempVal));
			else {
				//invalid year format
				tempMovie.setYear(0000);
				System.out.println("Invalid year format " + tempVal);
				
			}
		} else if (qName.equalsIgnoreCase("dirn")) {
			tempMovie.setDirector(tempVal);
		}

	}

	public void runExample() {
		parseDocument();
		printData();
		testMoviedb();
		//System.out.println(moviedb);
	}

	public static void main(String[] args) throws NamingException {
		MovieParser spe = new MovieParser();
		spe.runExample();
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
		runExample();
		return movies;
	}

	public Hashtable<ImmutableTriple<String, Integer, String>, Movie> getMoviedb() throws NamingException {

		Hashtable<ImmutableTriple<String, Integer, String>, Movie> hashtable = new Hashtable<ImmutableTriple<String, Integer, String>, Movie>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT * FROM movies";

		List<Movie> listMovies = jdbcTemplate.query(query, new RowMapper<Movie>() {

			@Override
			public Movie mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Movie movie = new Movie(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
						resultSet.getString(4), resultSet.getString(5), resultSet.getString(6));
				return movie;
			}

		});

		for (Movie movie : listMovies) {
			ImmutableTriple<String, Integer, String> key = new ImmutableTriple<String, Integer, String>(
					movie.getDirector(), movie.getYear(), movie.getTitle());

			hashtable.put(key, movie);
		}

		return hashtable;

	}

}
