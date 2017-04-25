package com.spring.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;

import com.spring.dao.*;
import com.spring.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovieController {

	@Autowired
	private MovieDao movieDao;

	@Autowired
	private GenreDao genreDao;

	@Autowired
	private StarDao starDao;

	@RequestMapping(value = "/searchForm")
	public String showSearchForm() {
		return "search";
	}

	@RequestMapping(value = "/search")
	public ModelAndView searchForMovies(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "column", required = false) String sortColumn,
			@RequestParam(value = "sort", required = false) String sort,
			RedirectAttributes redir) {
		
		System.out.println(sortColumn);
		System.out.println(sort);

		if (title.isEmpty() && first_name.isEmpty() && last_name.isEmpty() && year.isEmpty() && director.isEmpty()) {
			// return all movie list
			return new ModelAndView("index");
		}

		else {
			if (year.isEmpty())
				year = "-1";
		
			//TODO: take care of sorting (if sorting is not in the right format) ?
			if(sort == null)
			{
				sort = "a-z"; //default sorting
			}
			else //make sure sort in {a-z, z-a, 1-9, 9-1} only 
				sort = sort.toLowerCase(); 
			
			//sort by title by default
			if(sortColumn == null)
			{
				sortColumn = "Title";
			}
			
			else
			{
				sortColumn = sortColumn.toLowerCase();
			}
			
			//else: make sure its either year or title only
			
			ModelAndView model = new ModelAndView("movie-table-result");
			List<Movie> listMovies = movieDao.getMovieListWithSearch(title, Integer.parseInt(year), director,
					first_name, last_name, sortColumn , sort);

			Map<Integer, List<String>> listGenres = new HashMap<Integer, List<String>>();
			Map<Integer, List<String>> listStars = new HashMap<Integer, List<String>>();
			for (Movie movie : listMovies) {

				listGenres.put(movie.getId(), genreDao.getGenreListByMovieId(movie.getId()));
				listStars.put(movie.getId(), starDao.getStarsByMovieId(movie.getId()));
			}

			model.addObject("listMovies", listMovies);
			model.addObject("listGenres", listGenres);
			model.addObject("listStars", listStars);
			model.addObject("sort", sort);
			//model.addObject("column", sortColumn);
			return model;
		}

	}

	@RequestMapping("/test-table")
	public String show() {
		return "movie-table-result";
	}

	@RequestMapping("/sortMovieTitle")
	public ModelAndView sortMovieTitle(@RequestParam("sort") String desiredSort) {
		System.out.println(desiredSort);
		return null;
	}

	@RequestMapping("/sortMovieYear")
	public ModelAndView sortMovieYear(@RequestParam("sort") String desiredSort) {
		System.out.println(desiredSort);
		return null;
	}

	// @RequestMapping("/sortMovieYear")
	// {
	//
	// }
	//
}
