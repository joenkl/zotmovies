package com.spring.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.dao.CreditCardDao;
import com.spring.dao.CustomerDao;
import com.spring.dao.MovieDao;
import com.spring.dao.SaleDao;
import com.spring.model.Customer;
import com.spring.model.Movie;
import com.spring.model.Sale;
import com.spring.model.ShoppingCart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


@Controller
@RequestMapping(value="/shopping-cart")
public class ShoppingCartController {
	 
	@RequestMapping(value="/addcart", method=RequestMethod.POST)
	@Scope("session")
	public String addcart (@RequestParam(value="movieId", required=true) String movieId, 
			@RequestParam(value="name", required=false) String name 
			, HttpServletRequest request)
	{
		//check for correct parameter later.. maybe not necessary 
		ShoppingCart newItem = new ShoppingCart(Integer.parseInt(movieId), 1, name);
		
		HttpSession session = request.getSession(true);
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("cart");
		
		if(shoppingCartList == null)
		{
			//System.out.println("empty");
			shoppingCartList = new ArrayList<>();
			session.setAttribute("cart", shoppingCartList);
			
			shoppingCartList.add(newItem);
			
			//System.out.println(shoppingCartList);
		}
		else
		{
			boolean isDuplicate = false;
			
			//optimize later ...
			for(ShoppingCart cartItem : shoppingCartList){
				if (newItem.getMovieId() == cartItem.getMovieId())
				{
					cartItem.setQuantity(cartItem.getQuantity() + 1);
					isDuplicate = true;
					break;
				}
			}
			
			if (isDuplicate==false){
				shoppingCartList.add(newItem);
			}
			
			session.setAttribute("cart", shoppingCartList);
		}
			
		return "viewcart";
	}
	
	@RequestMapping(value="/updatecart", method=RequestMethod.POST)
	@Scope("session")
	public String updateQuantity (@RequestParam(value="movieId", required=true) String movieId, 
			@RequestParam(value="quantity", required=true) String quantity , HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("cart");
		
		int id = Integer.parseInt(movieId);
		int newQ = Integer.parseInt(quantity);
		
		for(ShoppingCart cartItem : shoppingCartList){
			if (id == cartItem.getMovieId())
			{
				if(newQ <= 0)
				{
					shoppingCartList.remove(cartItem);
				}
				else
				{
					cartItem.setQuantity(newQ);
				}
				break;
			}
		}
		return "viewcart";
	}
	
	@RequestMapping(value="/deleteItem", method=RequestMethod.POST)
	@Scope("session")
	public String removeItem (@RequestParam(value="movieId", required=true) String movieId, HttpServletRequest request)
	{
		HttpSession session = request.getSession(true);
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("cart");
		
		int id = Integer.parseInt(movieId);

		for(ShoppingCart cartItem : shoppingCartList){
			if (id == cartItem.getMovieId())
			{
				shoppingCartList.remove(cartItem);
				break;
			}
		}
		return "viewcart";
	}
	
	
	@RequestMapping(value="/payment-info")
	public String paymenInfo() {
		return ("creditcard-process");
	}
	
	@Autowired
	private CreditCardDao creditcardDao; 
	
	@RequestMapping(value="/credit-card-process", method=RequestMethod.POST)
	public ModelAndView creditCardProcess(HttpServletRequest request, RedirectAttributes redir){
		
		String expDate =  request.getParameter("yyyy") + "-" + 
							request.getParameter("mm") + "-" + 
							request.getParameter("dd");
		
		Boolean isCorrectCard = creditcardDao.isCorrectCard(request.getParameter("cardnumber"), 
				request.getParameter("fName"), request.getParameter("lName"), expDate);
		
		if (isCorrectCard){
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/shopping-cart/order-confirmation");
			HttpSession session = request.getSession(true);
			session.setAttribute("isCC", 1);
			return model; 
		}
		else{
			ModelAndView model = new ModelAndView();    
			model.setViewName("redirect:/shopping-cart/payment-info");
			redir.addFlashAttribute("message", "Your card information does not match our record");
			return model; 
		}
	}
	
	@RequestMapping(value="/payment-process")
	public ModelAndView paymentProcess(HttpServletRequest request, RedirectAttributes redir){
		
		HttpSession session = request.getSession(true);
		Integer login = (Integer) (session.getAttribute("login"));
		if (login == null)
		{
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/login");
			redir.addFlashAttribute("message", "Please login before checkout");
			return model; 
		}
		else{
			List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("cart");
			if (shoppingCartList==null || shoppingCartList.isEmpty()){
				ModelAndView model = new ModelAndView();    
				model.setViewName("redirect:/checkout");
				redir.addFlashAttribute("message", "Your Cart is empty");
				return model; 
			}
			else {
				ModelAndView model = new ModelAndView();   
				model.setViewName("redirect:/shopping-cart/payment-info");
				return model; 
			}
		}
		
	}
	
	
	@Autowired
	private SaleDao saleDao; 
	
	@Autowired
	private CustomerDao cusDao;
	
	@RequestMapping(value="/order-confirmation")
	public ModelAndView orderProcess(HttpServletRequest request, RedirectAttributes redir) throws ParseException{
		
		
		HttpSession session = request.getSession(true);
		
		if (session.getAttribute("login") == null || session.getAttribute("isCC") == null){
			return new ModelAndView("404-page");
		}
		@SuppressWarnings("unchecked")
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("cart");
		int cusID = (int) session.getAttribute("customerID");
		String cusName = (String) session.getAttribute("customerFN");
		
		List<Integer> movieIDList = new ArrayList<Integer>();
		int totalQ = 0;
		
		for(ShoppingCart cartItem : shoppingCartList){
			int itemQ = cartItem.getQuantity();
			totalQ += itemQ;
			int movieID = cartItem.getMovieId();
			for(int i = 0; i < itemQ; i++)
			{
				movieIDList.add(movieID);
			}
		}
		
		
		saleDao.addOrder(cusID, movieIDList);
		
		
		List<Sale> completedOrder = saleDao.getLatestOrder(totalQ);
		
		Customer customer = cusDao.getCustomer(cusID);
		
		ModelAndView model = new ModelAndView("order-confirmation");
		model.addObject("cusName", cusName);
		model.addObject("cusEmail", customer.getEmail());
		model.addObject("cusAddr", customer.getAddress());
		
		model.addObject("order", completedOrder);
		model.addObject("cost", session.getAttribute("totalCost"));
		model.addObject("scart", shoppingCartList);
		//empty shoppingCartList
		shoppingCartList.clear();
		session.setAttribute("totalCost", 0);
		session.removeAttribute("isCC");
		
		return model; 
	}
}
