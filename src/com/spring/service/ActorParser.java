package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.InputSource;

import com.spring.model.Movie;
import com.spring.model.Star;

import javafx.util.Pair;

//TODO: check for duplicate actors in xml files and database  
//same stagename and birthday 

public class ActorParser extends DefaultHandler {

	// star from db:
	private Hashtable<String, Star> stardb;
	private Hashtable<String, Star> starxml;
	private String tempVal;

	private Star tempStar;

	private DataSource dataSource;

	private FileWriter writer;

	boolean isClosedTag;

	public ActorParser() throws NamingException, IOException {

		File file = new File("star_log.txt");

		writer = new FileWriter(file);

		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");
		starxml = new Hashtable<String, Star>();
		stardb = getStardb();

		System.out.println("Logging for actors63.xml\n");

		isClosedTag = false;

	}

	private void parseDocument() {

		// get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			// get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			// get the file:
			File xmlFile = new File("WebContent/resources/xml/actors63.xml");

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
	 * 
	 * @throws IOException
	 */
	private void printData() throws IOException {

		System.out.println("No of Stars '" + starxml.size() + "'.");

		// Iterator<Star> it = myStars.iterator();
		// while (it.hasNext()) {
		// System.out.println(it.next().toString());
		// }
	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("actor")) {
			tempStar = new Star();
			isClosedTag = false;
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("Actor")) {

			boolean isDuplicateClosedTag = false;

			if (!isClosedTag)
				isClosedTag = true;
			else
				isDuplicateClosedTag = true;

			if (isClosedTag && !isDuplicateClosedTag) {
				String key = tempStar.getStagename().toLowerCase();
				
				boolean isDuplicate = starxml.containsKey(tempStar.getStagename().toLowerCase()) || stardb.containsKey(key);

				if (starxml.containsKey(key)) {
					System.out
							.println("Encounter duplicate star in actors63.xml where star= " + tempStar.getStagename());
				}

				if (stardb.containsKey(key)) {
					System.out.println("Encounter duplicate star with database where star= " + key);
				}

				// insert if stars hashtable is empty or it's a new actor
				if ( !isDuplicate)
					starxml.put(key, tempStar);
			} else {

				System.out.println("Encounter double end tag with starName= " + tempStar.getStagename());

			}

		} else if (qName.equalsIgnoreCase("stagename")) {
			// parse first name and last name:
			String[] parts = tempVal.split("[~\\s@&.?$+-]+");
			String last_name = parts[parts.length - 1];

			String first_name = "";
			// first name:
			for (int i = 0; i < parts.length - 1; i++)
				first_name += parts[i];

			tempStar.setFirst_name(first_name);
			tempStar.setLast_name(last_name);
			tempStar.setStagename(tempVal);
		} else if (qName.equalsIgnoreCase("dob")) {
			tempStar.setsDOB(tempVal);
		}

	}

	public void run() throws IOException {
		parseDocument();
//
//		System.out.println(starxml.size());
//		System.out.println(stardb.size());
	}

	
	public Hashtable<String, Star> getStarXML(){
		return this.starxml;
	}
	
	public Hashtable<String, Star> getStarDB(){
		return this.stardb;
	}
	
	public Hashtable<String, Star> getStardb() throws NamingException {

		Hashtable<String, Star> hashtable = new Hashtable<String, Star>();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String query = "SELECT * FROM stars";

		List<Star> listStars = jdbcTemplate.query(query, new RowMapper<Star>() {

			@Override
			public Star mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
				Star star = new Star();
				star.setId(resultSet.getInt(1));
				star.setLast_name(resultSet.getString(2));
				star.setFirst_name(resultSet.getString(3));
				star.setDob(resultSet.getDate(4));
				star.setPhoto_url(resultSet.getString(5));
				return star;
			}

		});

		for (Star star : listStars) {
			// String year = Integer.toString(star.getDob().getYear());
			String key = star.getFirst_name().toLowerCase() + " " + star.getLast_name().toLowerCase();

			hashtable.put(key, star);
		}

		return hashtable;

	}

	public static void main(String[] args) throws NamingException, IOException {
		ActorParser spe = new ActorParser();
		spe.run();
	}

}
