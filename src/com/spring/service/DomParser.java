package com.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.spring.model.Genre;
import com.spring.model.Movie;
import com.spring.model.Star;

public class DomParser {
	List<Movie> movies;
	// List<Genre> genres;
	// List<Star> stars;

	public DomParser() {
		movies = new ArrayList<Movie>();
		// genres = new ArrayList();
		// stars = new ArrayList();
	}
	
	//TODO: separate functions for parseMains, parseCasts, parseActors

	public static void main(String[] args) {

		// get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {

			// Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			
			// parse using builder to get DOM representation of the XML file
			File fXmlFile = new File("WebContent/resources/xml/mains243.xml");
			Document doc = db.parse(fXmlFile);
			
			//normalize doc
			doc.getDocumentElement().normalize();
			
			/*---------------------------------------------------------PARSE MAINS------------------------------------------------------------------------*/
			//test root element => root is <movies>
			//System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
			
			//after traversing to <movies>, traverse to <directorfilms>
			NodeList directorFilms = doc.getElementsByTagName("directorfilms");
			
			//directorfilms has 2 children => <director> & <films>
			HashMap<String, NodeList> directors_and_films = new HashMap<String, NodeList>();
			HashMap<String, List<Node>> movies_and_genres = new HashMap<String, List<Node>>();
			for(int i = 0; i < directorFilms.getLength(); i++)
			{
				Node tempNode = directorFilms.item(i);
				
			
				if(tempNode.hasChildNodes())
				{
					//children: <director> and <films>
					Node director = tempNode.getChildNodes().item(0);
					Node films = tempNode.getChildNodes().item(1);
					if(director != null && films != null) //just to be sure
					{
						Element eDirector = (Element) director;
						String dirName = "";
						if(eDirector.getElementsByTagName("dirname").item(0) != null) //in case dirname is not provided <dirname><siteplace>
							dirName = (eDirector.getElementsByTagName("dirname").item(0).getTextContent());
						//if dirName is not empty then add
//						if(!dirName.isEmpty())
//							System.out.println(dirName);
						
						NodeList listFilms = (NodeList)films;
					
						
						if(!dirName.isEmpty()) //and check for duplicate 
						directors_and_films.put(dirName, listFilms);
						
						
					}
					
					
					System.out.println(directors_and_films);
					
					
				}
			}
			
			
		
			
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

}
