package com.spring.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

public class PopulateDatabase {

	public static void main(String[] args) throws NamingException {
		//Connect to database:
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/moviedb");
		
		//construct hashtable:
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT * FROM movies";
		
		//hash table init:
		Hashtable movies = new Hashtable();
		
		
		

	}

}
