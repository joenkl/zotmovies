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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

	@Autowired
	ServletContext context;

	@GetMapping("/api/search")
	public List<Movie> search(@RequestParam(value = "title", required = false) String query) {

		long startTime = System.nanoTime();
		if (query == null || query.isEmpty()) {
			return new ArrayList<Movie>();
		}

		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return (movieDao.fuzzy_search(query));
	}

	@GetMapping("/api/searchTitle")
	public List<String> searchTitle(@RequestParam(value = "title", required = false) String query) {

		long startTime = System.nanoTime();
		if (query == null || query.isEmpty()) {
			return new ArrayList<String>();
		}

		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return (movieDao.api_search(query));
	}

	public void logRequest(String data) {
		String path = context.getRealPath("/WEB-INF");

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {
			// String data = "new content";

			File file = new File(path, "TS_TJ_logs.txt");

			if (!file.exists()) {
				file.createNewFile();
			}

			// if file exists already, then append file
			fw = new FileWriter(file.getAbsolutePath(), true);
			bw = new BufferedWriter(fw);

			bw.write("TS = " + data + "\n");

			System.out.println("Logged!");

		}

		catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			}

			catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
