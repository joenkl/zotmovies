package com.spring.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;
import com.spring.dao.MovieDao;
import com.spring.model.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SearchAPI {

	@Autowired
	MovieDao movieDao;

	@GetMapping("/api/search")
	public List<Movie> search(@RequestParam(value = "title", required = false) String query) {
		if(query == null || query.isEmpty())
		{
			return new ArrayList<Movie>(); 
		}
		
		String[] words = query.split(" ");

		String stmt = "SELECT * FROM movies WHERE MATCH(title) AGAINST('";

		for (String word : words) {
			if (word.equals("+") || word.equals("-")
					|| word.equals("*")
					|| word.equals("/")
					|| word.equals("-") 
					|| word.equals(">")
					|| word.equals("<")
					|| word.equals("%")
					|| word.equals(")")
					|| word.equals("(")
					|| word.equals("~")
					|| word.equals("@")
					|| word.equals("\"")) break; 
			if(word.equals("'") 
					|| word.equals("\b")
					|| word.equals("\n")
					|| word.equals("\t")
					|| word.equals("\\")
					
					)
			{
				stmt += "+\\" + word + "* ";
			}
			else
				stmt += "+" + word + "* ";

		}
		stmt += "' IN BOOLEAN MODE)";

		System.out.println(stmt);
		return (movieDao.fuzzy_search(stmt));
	}

	@GetMapping("/api/searchTitle")
	public List<String> searchTitle(@RequestParam(value = "title", required = false) String query) {
		if(query == null || query.isEmpty())
		{
			return new ArrayList<String>(); 
		}
		
		String[] words = query.split(" ");

		String stmt = "SELECT title FROM movies WHERE MATCH(title) AGAINST('";

		for (String word : words) {
			if (word.equals("+") || word.equals("-")
					|| word.equals("*")
					|| word.equals("/")
					|| word.equals("-") 
					|| word.equals(">")
					|| word.equals("<")
					|| word.equals("%")
					|| word.equals(")")
					|| word.equals("(")
					|| word.equals("~")
					|| word.equals("@")
					|| word.equals("\"")) break; 
			if(word.equals("'") 
					|| word.equals("\b")
					|| word.equals("\n")
					|| word.equals("\t")
					|| word.equals("\\")
					
					)
			{
				stmt += "+\\" + word + "* ";
			}
			else
				stmt += "+" + word + "* ";

		}
		stmt += "' IN BOOLEAN MODE)";

		System.out.println(stmt);
		return (movieDao.api_search(stmt));
	}
}
