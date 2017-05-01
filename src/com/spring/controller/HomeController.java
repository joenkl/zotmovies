
package com.spring.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.spring.dao.*;
import com.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; 
@Controller
public class HomeController {
	
	@Autowired
	private MovieDao movieDao; 
	

	@RequestMapping("/titles")
	public String titles() {
		return ("titles");
	}
	
	@RequestMapping("/checkout")
	public String checkout() {
		return ("checkout");
	}
	
	@RequestMapping("/reports/like-predicate")
	public String report() {
		return ("report");
	}
	
	private Boolean tryParseInt(String number) {
		try {
			Integer.parseInt(number);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
