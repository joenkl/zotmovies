package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.naming.NamingException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.spring.model.Movie;
import com.spring.model.Star;

public class CastParser extends DefaultHandler {
	// hash table of movies
	private Hashtable<String, Movie> movies;
	private String tempVal;

	private Movie tempMovie;

	private MovieParser mp;

	public CastParser() throws NamingException {
		mp = new MovieParser();
		movies = mp.getMoviesHashTable();

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
//		for (Entry<String, Movie> entry : movies.entrySet()) {
//		    String key = entry.getKey();
//		    Movie value = entry.getValue();
//
//		    System.out.println ("Key: " + key + " Value: " + value);
//		}
	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("f")) {
			tempMovie = new Movie();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("f")) {
			if (!tempVal.isEmpty()) {
				if (movies.containsKey(tempVal)) {
					tempMovie = movies.get(tempVal);
				}
				
				else{
					System.out.println("fid in casts124.xml doesn't exist in mains243.xml");
				}
			}
			
			else{
				System.out.println("fid doesn't exist in casts124.xml");
			}
		}

		else if (qName.equals("a")) {
			if(!(tempMovie.getFid().equals("N/A")))
			{
				Star tempStar = new Star();
				tempStar.setLast_name(tempVal);
				tempMovie.addStar(tempStar);
				
				//movie has unknown actors would be removed
				if(tempVal.equals("s a"))
				{
					System.out.println("Encounter unknown actor for " + tempMovie + " in casts124.xml");
					movies.remove(tempMovie.getFid());
				}
			}

		}

	}

	public void runExample() {
		parseDocument();
		printData();
	}

	public static void main(String[] args) throws NamingException {
		CastParser spe = new CastParser();
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
		return movies;
	}

}
