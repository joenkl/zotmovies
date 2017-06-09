package com.spring.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
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
	
	@Autowired
	ServletContext context;


	@RequestMapping(value = "/searchForm")
	public String showSearchForm() {
		return "search";
	}


	@RequestMapping("/index")
	public ModelAndView home(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "n", required = false) String nPerPage) throws IOException {
		long startTime = System.nanoTime();
		////////////////////////////////////////////////
		int defaultN = 6;

		nPerPage = prepareNperPage(nPerPage, defaultN);

		page = preparePage(page);

		if (page.equals("invalid"))
			return new ModelAndView("404-page");

		if (nPerPage.equals("invalid"))
			return new ModelAndView("404-page");

		List<Movie> listMovies = movieDao.getMovieList(Integer.parseInt(page), Integer.parseInt(nPerPage));
		ModelAndView model = new ModelAndView("index");
		//
		// //shuffle list:
		// long seed = System.nanoTime();
		// Collections.shuffle(listMovies, new Random(seed));

		model.addObject("listMovies", listMovies);
		model.addObject("activePage", page);
		model.addObject("currentPage", "index");

		// for showing n per page:
		model.addObject("minPage", defaultN);
		model.addObject("n", nPerPage);

		model.addObject("path", "index");

		// for pagination:
		if (listMovies.size() < Integer.parseInt(nPerPage))
			model.addObject("lastPage", true);
		else
			model.addObject("lastPage", false);

		////////////////////////////////////////////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));
		return model;
	}

	@RequestMapping(value = "/tokenSearch")
	public ModelAndView tokenSearch(@RequestParam(value = "title", required = false) String title) {

		long startTime = System.nanoTime();
		////////////////////////////////////////////////

		if (title == null || title.isEmpty()) {
			ModelAndView model = new ModelAndView("searchToken");
			model.addObject("listMovies", new ArrayList<Movie>());
			return model;
		}

		List<Movie> listMovies = movieDao.fuzzy_search(title);

		ModelAndView model = new ModelAndView("searchToken");
		model.addObject("listMovies", listMovies);

		////////////////////////////////////////////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return model;

	}

	@RequestMapping(value = "/search")
	public ModelAndView searchForMovies(@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "first_name", required = false) String first_name,
			@RequestParam(value = "last_name", required = false) String last_name,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "director", required = false) String director,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "n", required = false) String nPerPage, RedirectAttributes redir) {

		long startTime = System.nanoTime();
		////////////////////////////////////////////////
		if (title == null)
			title = "";
		if (first_name == null)
			first_name = "";
		if (last_name == null)
			last_name = "";
		if (year == null)
			year = "";
		if (director == null)
			director = "";
		if (title.isEmpty() && first_name.isEmpty() && last_name.isEmpty() && year.isEmpty() && director.isEmpty()) {
			// return all movie list

			ModelAndView model = new ModelAndView("search");
			model.addObject("message", "Please fill out at least one option!");

			////////////////////////////////////////////////////////////
			long endTime = System.nanoTime();

			logRequest(Long.toString(endTime - startTime));

			return model;

		}

		else {
			if (year.isEmpty())
				year = "-1";

			if (!tryParseInt(year)) {
				return new ModelAndView("404-page");
			}

			// prepare n per page:
			int defaultN = 6;
			nPerPage = prepareNperPage(nPerPage, defaultN);
			if (nPerPage.equals("invalid"))
				return new ModelAndView("404-page");

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
					first_name, last_name, column, sort, Integer.parseInt(page), Integer.parseInt(nPerPage));

			ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies, nPerPage);

			// for showing n per page:
			model.addObject("minPage", defaultN);
			model.addObject("n", nPerPage);
			model.addObject("path", "search");

			////////////////////////////////////////////////////////////
			long endTime = System.nanoTime();

			logRequest(Long.toString(endTime - startTime));

			return model;
		}

	}

	@RequestMapping("/movie-id={condition}")
	public ModelAndView browseMovieByID(@PathVariable("condition") int id) {

		long startTime = System.nanoTime();
		////////////////////////////////////////////////

		Movie movie = movieDao.getMovieListWithID(id);
		ModelAndView model = new ModelAndView("movie-info");

		model.addObject("movie", movie);

		List<Genre> listGenres = genreDao.getGenreListByMovieId(movie.getId());
		model.addObject("listGenres", listGenres);
		model.addObject("listStars", starDao.getStarsByMovieId(movie.getId()));

		////////////////////////////////////////////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return model;
	}

	@RequestMapping("/browseTitle")
	public ModelAndView titleBrowsing(@RequestParam(value = "startWith") String browserTerm,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "n", required = false) String nPerPage,
			@RequestParam(value = "page", required = false) String page) {

		long startTime = System.nanoTime();
		////////////////////////////////////////////////

		// prepare n per page:
		int defaultN = 6;
		nPerPage = prepareNperPage(nPerPage, defaultN);
		if (nPerPage.equals("invalid"))
			return new ModelAndView("404-page");

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

		List<Movie> listMovies = movieDao.getMovieListWhereTitlesStartWith(browserTerm, column, sort,
				Integer.parseInt(page), Integer.parseInt(nPerPage));

		ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies, nPerPage);

		String currentPage = "browseTitle?startWith=" + browserTerm;
		model.addObject("currentPage", currentPage);

		// for showing n per page:
		model.addObject("minPage", defaultN);
		model.addObject("n", nPerPage);
		model.addObject("path", "browseTitle");

		////////////////////////////////////////////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

		return model;
	}

	@RequestMapping("/browseGenre")
	public ModelAndView genreBrowsing(@RequestParam(value = "genre") String genre,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "n", required = false) String nPerPage,
			@RequestParam(value = "page", required = false) String page) {

		long startTime = System.nanoTime();
		////////////////////////////////////////////////

		// prepare n per page:
		int defaultN = 6;
		nPerPage = prepareNperPage(nPerPage, defaultN);
		if (nPerPage.equals("invalid"))
			return new ModelAndView("404-page");

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

		List<Movie> listMovies = movieDao.getMovieListWithGenre(genre, column, sort, Integer.parseInt(page),
				Integer.parseInt(nPerPage));

		ModelAndView model = prepareForMovieTableResult(sort, column, page, listMovies, nPerPage);

		String currentPage = "browseGenre?genre=" + genre;
		model.addObject("currentPage", currentPage);

		// for showing n per page:
		model.addObject("minPage", defaultN);
		model.addObject("n", nPerPage);
		model.addObject("path", "browseGenre");

		////////////////////////////////////////////////////////////
		long endTime = System.nanoTime();

		logRequest(Long.toString(endTime - startTime));

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

	private String prepareNperPage(String nPerPage, int defaultValue) {
		if (nPerPage == null || nPerPage.isEmpty()) {
			nPerPage = Integer.toString(defaultValue);
		} else if (!tryParseInt(nPerPage)) {
			nPerPage = "invalid";

		}

		return nPerPage;
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

	private ModelAndView prepareForMovieTableResult(String sort, String column, String page, List<Movie> listMovies,
			String nPerPage) {
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

		// for pagination:
		if (listMovies.size() < Integer.parseInt(nPerPage))
			model.addObject("lastPage", true);
		else
			model.addObject("lastPage", false);

		return model;
	}

	@RequestMapping(value = "/_movie-confirmation", method = RequestMethod.POST)
	public ModelAndView addNewMovie(@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "director", required = true) String director,
			@RequestParam(value = "year", required = true) Integer year,
			@RequestParam(value = "banner_url", required = false) String banner_url,
			@RequestParam(value = "trailer_url", required = false) String trailer_url,

			@RequestParam(value = "genre", required = false) String genre,

			@RequestParam(value = "first_name", required = false) String starFN,
			@RequestParam(value = "last_name", required = true) String starLN,
			@RequestParam(value = "dob", required = true) java.sql.Date starDob,
			@RequestParam(value = "photo_url", required = false) String starPhotoURL) {
		ModelAndView model = new ModelAndView("_movie-confirmation");

	
		if (banner_url == null)
			banner_url = "";
		if (trailer_url == null)
			trailer_url = "";

		String msg = movieDao.addMovieProcedure(title, year, director, banner_url, trailer_url, starFN, starLN, starDob,
				starPhotoURL, genre);

		model.addObject("msg", msg);

	

		return model;

	}

	@RequestMapping("/searchBox")
	public ModelAndView showSearchBox() {
		return new ModelAndView("fuzzysearch");
	}

	@RequestMapping(value = "/tool-movie-id={condition}", method = RequestMethod.GET)
	public ModelAndView tooltipMovieByID(@PathVariable("condition") int id) {

		
		Movie movie = movieDao.getMovieListWithID(id);
		ModelAndView model = new ModelAndView("tool-movie-info");

		model.addObject("movie", movie);

		List<Genre> listGenres = genreDao.getGenreListByMovieId(movie.getId());
		model.addObject("listGenres", listGenres);
		model.addObject("listStars", starDao.getStarsByMovieId(movie.getId()));

	

		return model;
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
