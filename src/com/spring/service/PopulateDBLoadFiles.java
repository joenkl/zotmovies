package com.spring.service;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PopulateDBLoadFiles {
	
	private static DataSource dataSource; 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//get dataSource
		ApplicationContext ctx = new ClassPathXmlApplicationContext("file:src/com/spring/config/dataSource_config.xml");
		dataSource = (DataSource) ctx.getBean("dataSource");

	}

}
