package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.spring.dao.MovieDao;
import com.spring.model.Movie;
import com.spring.model.ShoppingCart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


@Controller
@RequestMapping(value="/shopping-cart")
public class ShoppingCartController {
	
	
	@Autowired
	private CreditCardDao creditcartDao; 
	
	@RequestMapping(value="/sp-form")
	public ModelAndView indexSP()
	{
		//cart.put("cart", new ShoppingCart());
		ModelAndView model = new ModelAndView("cart");
		ShoppingCart cart = new ShoppingCart();
		
		model.addObject("cart", cart);
		return model; 
		
	}
	
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
				if(newQ == 0)
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
	
	@RequestMapping(value="/credit-card-process", method=RequestMethod.POST)
	public ModelAndView creditCardProcess(HttpServletRequest request, RedirectAttributes redir){
		
		String expDate =  request.getParameter("yyyy") + "-" + 
							request.getParameter("mm") + "-" + 
							request.getParameter("dd");
		Boolean isCorrectCard = creditcartDao.isCorrectCard(request.getParameter("cardnumber"), 
				request.getParameter("fName"), request.getParameter("lName"), expDate);
		
		if (isCorrectCard){
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/checkout");
			redir.addFlashAttribute("message", "Your card info is correct");
			return model; 
		}
		else{
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/checkout");
			redir.addFlashAttribute("message", "Your card information does not match our record");
			return model; 
		}
	}
}
