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
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "page", required = false) String page, RedirectAttributes redir) {

		if (title.isEmpty() && first_name.isEmpty() && last_name.isEmpty() && year.isEmpty() && director.isEmpty()) {
			// return all movie list
			return new ModelAndView("index");
		}

		else {
			if (year.isEmpty())
				year = "-1";

			// TODO: take care of sorting (if sorting is not in the right
			// format) ?
			if (sort == null) {
				sort = "a-z"; // default sorting
			} else // make sure sort in {a-z, z-a, 1-9, 9-1} only
			{
				if(sort.isEmpty())
					sort = "a-z";

				sort = sort.toLowerCase();

				if (!sort.equals("a-z") && !sort.equals("z-a") && !sort.equals("1-9") && !sort.equals("9-1")) {
					System.out.println("invalid sort problems.");
					return new ModelAndView("404-page");
				}
			}

			// sort by title by default
			if (column == null) {
				column = "title";
			}

			else {
				
				if(column.isEmpty())
					column = "title";
				
				// else: make sure its either year or title only
				column = column.toLowerCase();
				if (!column.equals("title") && !column.equals("year")) {
					System.out.println("invalid column problems.");
					return new ModelAndView("404-page");
				}
			}

			// pagination:
			if (page == null) {
				page = "1";
			}

			// else: make sure it's a number
			else {
				
				if (page.isEmpty())
					page = "1";
				
				if (!tryParseInt(page)) {
					System.out.println("try parse int problems.");
					return new ModelAndView("404-page");
				}
				
				else if(Integer.parseInt(page) <= 0) 
				{
					System.out.println("invalid page problems.");
					return new ModelAndView("404-page");
				}

			}

			ModelAndView model = new ModelAndView("movie-table-result");
			List<Movie> listMovies = movieDao.getMovieListWithSearch(title, Integer.parseInt(year), director,
					first_name, last_name, column, sort, Integer.parseInt(page));

			Map<Integer, List<String>> listGenres = new HashMap<Integer, List<String>>();
			Map<Integer, List<String>> listStars = new HashMap<Integer, List<String>>();

			// create hash table for listGenres and listStars
			for (Movie movie : listMovies) {

				listGenres.put(movie.getId(), genreDao.getGenreListByMovieId(movie.getId()));
				listStars.put(movie.getId(), starDao.getStarsByMovieId(movie.getId()));
			}

			model.addObject("listMovies", listMovies);
			model.addObject("listGenres", listGenres);
			model.addObject("listStars", listStars);
			model.addObject("sort", sort);
			model.addObject("activePage", page);

			if ((listMovies).size() < 10)
				model.addObject("lastPage", true);

			else
				model.addObject("lastPage", false);

			return model;
		}

	}

	@RequestMapping("/test-table")
	public String show() {
		return "movie-table-result";
	}

	@RequestMapping("/test-pagination")
	public String show_pagination() {
		return "pagination";

	}

	@RequestMapping("/sortMovieYear")
	public ModelAndView sortMovieYear(@RequestParam("sort") String desiredSort) {
		System.out.println(desiredSort);
		return null;
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
