package com.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
	
	@Controller
	public class HttpErrorController {

	    @RequestMapping(value="/error404")
	    public String error404() {
	        return "404-page";
	    }
	}
}
