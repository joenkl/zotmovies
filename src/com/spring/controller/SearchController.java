package com.spring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController {
	@RequestMapping("/searchBox")
	public ModelAndView showSearchBox(){
		return new ModelAndView("fuzzysearch");
	}
}
	