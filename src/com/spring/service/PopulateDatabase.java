package com.spring.service;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jndi.JndiTemplate;

public class PopulateDatabase {
	
	private static DataSource dataSource; 

	public static void main(String[] args) throws NamingException {
		
		//get dataSource
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");
		
		/*
		 * Connection connection = dataSource.getConnection();
			Connection.setAutoCommit(false);   // Disables auto-commit.
		 */
		
		
		
	
		
		
		

	}

}
