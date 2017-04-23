package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.spring.dao.GenreDao;
import com.spring.model.Genre;


import org.springframework.stereotype.Controller;


@Controller 
public class GenreController {
	
	@Autowired
	private GenreDao genreDao; 
	
	@RequestMapping(value={"/genres"})
	public ModelAndView genre()
	{
		List<Genre> listOfGenres = genreDao.getGenreList();
		ModelAndView model = new ModelAndView("genres");
		model.addObject("listOfGenres", listOfGenres);
		return model; 
	}

}
