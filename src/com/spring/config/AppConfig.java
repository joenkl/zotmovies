package com.spring.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class AppConfig  extends WebMvcConfigurerAdapter {
	 @Autowired
	 private Environment env;
	@Bean(name="dataSource")
	 public DataSource dataSource() throws NamingException {
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = (DataSource) jndiTemplate.lookup("java:comp/env/jdbc/moviedb");
        return dataSource;
    }

}
