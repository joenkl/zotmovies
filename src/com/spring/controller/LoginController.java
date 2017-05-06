package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.dao.CustomerDao;
import com.spring.model.Customer;
import com.spring.service.VerifyUtils;

@Controller
public class LoginController {

	@Autowired
	CustomerDao customerdao;

	@RequestMapping(value = { "", "/", "/login" })
	public String login(HttpServletRequest request, RedirectAttributes redir) {

		HttpSession session = request.getSession(true);
		Integer login = (Integer) (session.getAttribute("login"));

		if (login == null) {
			String referrer = request.getHeader("Referer");
			request.getSession(true).setAttribute("url_prior_login", "/index");

			// if(!referrer.contains("checkout"))
			if (referrer != null && referrer.contains("checkout")) {
				request.getSession(true).setAttribute("url_prior_login", referrer);
			}

			// else if (referrer.contains("login")) {
			// request.getSession(true).setAttribute("url_prior_login",
			// "/index");
			// }
			//
			// else if (!referrer.contains(request.getServerName()) ||
			// !referrer.contains(request.getContextPath())) {
			// request.getSession(true).setAttribute("url_prior_login",
			// "/index");
			// }

			return "login";
		} else
			return "redirect:/index";
	}

	@RequestMapping(value = "/processLoginForm", method = RequestMethod.POST)
	public ModelAndView processLoginForm(HttpServletRequest request, RedirectAttributes redir) {

		// get recaptcha response:
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		// verify CAPTCHA
		boolean valid = VerifyUtils.verify(gRecaptchaResponse);

		if (!valid) {
			// TODO: this is just for testing => later on should change
			return new ModelAndView("404-page");
		}

		else {

			Boolean isCustomer = customerdao.isCustomer(request.getParameter("email"),
					request.getParameter("password"));
			HttpSession session = request.getSession(true);

			if (isCustomer) {
				// TODO: change session

				Integer login = (Integer) (session.getAttribute("login"));
				if (login == null) {
					login = new Integer(1);
					session.setAttribute("login", login);
					Customer customer = customerdao.getCustomerInfo(request.getParameter("email"),
							request.getParameter("password"));
					session.setAttribute("customerID", customer.getId());
					String fullname = customer.getFist_name() + " " + customer.getLast_name();
					session.setAttribute("customerFN", fullname);
				}

				String redirectUrl = (String) session.getAttribute("url_prior_login");
				System.out.println(redirectUrl);

				ModelAndView model = new ModelAndView();
				model.setViewName("redirect:" + redirectUrl);
				return model;
			}

			else {
				ModelAndView model = new ModelAndView();
				model.setViewName("redirect:/login");
				redir.addFlashAttribute("message", "Invalid Email/Password combination. Please try again!");
				return model;
			}
		}

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView processLogout(HttpServletRequest request, RedirectAttributes redir) {

		HttpSession session = request.getSession(true);
		session.removeAttribute("login");
		session.removeAttribute("customerID");
		session.removeAttribute("customerFN");
		return new ModelAndView("redirect:/login");
	}
}
