package com.spring.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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

			// prepare sort:
			sort = prepareSort(sort);
			if (sort.equals("invalid"))
				return new ModelAndView("404-page");

			// prepare column
			column = prepareColumn(column);
			if (column.equals("invalid"))
				return new ModelAndView("404-page");

			// prepare page:
			page = preparePage(page);
			if (page.equals("invalid"))
				return new ModelAndView("404-page");

			List<Movie> listMovies = movieDao.getMovieListWithSearch(title, Integer.parseInt(year), director,
					first_name, last_name, column, sort, Integer.parseInt(page));

			ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies);

			return model;
		}

	}

	@RequestMapping("/movie-id={condition}")
	public ModelAndView browseMovieByID(@PathVariable("condition") int id) {
		Movie movie = movieDao.getMovieListWithID(id);
		ModelAndView model = new ModelAndView("movie-info");

		model.addObject("movie", movie);
		
		List<Genre> listGenres = genreDao.getGenreListByMovieId(movie.getId());
		model.addObject("listGenres", listGenres);
		model.addObject("listStars", starDao.getStarsByMovieId(movie.getId()));

		return model;
	}

	@RequestMapping("/browseTitle")
	public ModelAndView titleBrowsing(@RequestParam(value = "startWith") String browserTerm,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "page", required = false) String page) {

		// prepare sort:
		sort = prepareSort(sort);
		if (sort.equals("invalid"))
			return new ModelAndView("404-page");

		// prepare column
		column = prepareColumn(column);
		if (column.equals("invalid"))
			return new ModelAndView("404-page");

		// prepare page:
		page = preparePage(page);
		if (page.equals("invalid"))
			return new ModelAndView("404-page");

		if (page.equals("invalid")) {
			return new ModelAndView("404-page");
		}

		List<Movie> listMovies = movieDao.getMovieListWhereTitlesStartWith(browserTerm, column, sort, Integer.parseInt(page));

		ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies);

		String currentPage = "browseTitle?startWith=" + browserTerm;
		model.addObject("currentPage", currentPage);

		return model;
	}

	@RequestMapping("/browseGenre")
	public ModelAndView genreBrowsing(@RequestParam(value = "genre") String genre,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "page", required = false) String page) {

		// prepare sort:
		sort = prepareSort(sort);
		if (sort.equals("invalid"))
			return new ModelAndView("404-page");

		// prepare column
		column = prepareColumn(column);
		if (column.equals("invalid"))
			return new ModelAndView("404-page");

		// prepare page:
		page = preparePage(page);
		if (page.equals("invalid"))
			return new ModelAndView("404-page");

		List<Movie> listMovies = movieDao.getMovieListWithGenre(genre, column, sort, Integer.parseInt(page));

		ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies);

		String currentPage = "browseGenre?genre=" + genre;
		model.addObject("currentPage", currentPage);

		return model;

	}

	private Boolean tryParseInt(String number) {
		try {
			Integer.parseInt(number);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private String preparePage(String page) {
		if (page == null) {
			page = "1";
		}

		else {
			if (page.isEmpty())
				page = "1";

			else if (!tryParseInt(page))
				page = "invalid";

			else if (Integer.parseInt(page) <= 0) {
				page = "invalid";
			}

		}

		return page;

	}

	private String prepareColumn(String column) {
		if (column == null) {
			column = "title";
		}

		else {

			if (column.isEmpty())
				column = "title";

			// else: make sure its either year or title only
			column = column.toLowerCase();
			if (!column.equals("title") && !column.equals("year")) {
				column = "invalid";
			}
		}

		return column;
	}

	private String prepareSort(String sort) {
		if (sort == null) {
			sort = "a-z"; // default sorting
		} else // make sure sort in {a-z, z-a, 1-9, 9-1} only
		{
			if (sort.isEmpty())
				sort = "a-z";

			sort = sort.toLowerCase();

			if (!sort.equals("a-z") && !sort.equals("z-a") && !sort.equals("1-9") && !sort.equals("9-1")) {
				sort = "invalid";
			}
		}

		return sort;

	}

	private ModelAndView prepareForMovieTableResult(String sort, String column, String page, List<Movie> listMovies) {
		ModelAndView model = new ModelAndView("movie-table-result");

		if ((listMovies).size() < 10)
			model.addObject("lastPage", true);

		else
			model.addObject("lastPage", false);

		Map<Integer, List<Genre>> listGenres = new HashMap<Integer, List<Genre>>();
		Map<Integer, List<Star>> listStars = new HashMap<Integer, List<Star>>();

		// create hash table for listGenres and listStars
		for (Movie movie : listMovies) {

			listGenres.put(movie.getId(), genreDao.getGenreListByMovieId(movie.getId()));
			listStars.put(movie.getId(), starDao.getStarsByMovieId(movie.getId()));
		}

		model.addObject("listMovies", listMovies);
		model.addObject("listGenres", listGenres);
		model.addObject("listStars", listStars);
		model.addObject("activePage", page);
		model.addObject("sort", sort);
		model.addObject("column", column);

		return model;
	}

}
