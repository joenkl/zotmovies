package com.spring.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.InputSource;

import com.spring.model.Star;

import javafx.util.Pair;

//TODO: check for duplicate actors in xml files and database  
//same stagename and birthday 

public class ActorParser extends DefaultHandler {
	private Hashtable<Pair<String, String>, Star> stars; 
	private String tempVal;

	private Star tempStar;

	public ActorParser() {
		stars = new Hashtable<Pair<String, String>, Star>(); 

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
	 */
	private void printData() {

		System.out.println("No of Stars '" + stars.size() + "'.");

//		Iterator<Star> it = myStars.iterator();
//		while (it.hasNext()) {
//			System.out.println(it.next().toString());
//		}
	}

	// Event Handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		// reset:
		tempVal = "";
		if (qName.equalsIgnoreCase("actor")) {
			tempStar = new Star();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
		tempVal = new String(ch, start, length);
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("Actor")) {
			
			Pair<String, String> key = new Pair<String, String>(tempStar.getLast_name(),tempStar.getsDOB());
			
			//if star already exist:
			if(stars.containsKey(key))
				System.out.println("Duplicate star in actors63.xml");
			//insert if stars hashtable is empty or it's a new actor 
			if(stars.isEmpty() || 
					(!stars.containsKey(key)))
				stars.put(key, tempStar);
			
			else
			{
				System.out.println("Cannot insert the following into database: ");
				System.out.println(tempStar);
				
			}
			

		} else if (qName.equalsIgnoreCase("stagename")) {
			// TODO: parse last name & first name
			//
			// String FN = tempVal.substring(0, tempVal.lastIndexOf(" "));
			// String LN = tempVal.substring(tempVal.lastIndexOf(" "));
			//
			// System.out.println(FN);
			// System.out.println(LN);
			// tempStar.setFirst_name(FN);
			tempStar.setLast_name(tempVal);
		} else if (qName.equalsIgnoreCase("dob")) {
			tempStar.setsDOB(tempVal);
		}

	}

	public void runExample() {
		parseDocument();
		printData();
	}

	public static void main(String[] args) {
		ActorParser spe = new ActorParser();
		spe.runExample();
	}

}
