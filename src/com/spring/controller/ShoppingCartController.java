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

import com.spring.model.ShoppingCart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


@Controller
@RequestMapping(value="/shopping-cart")
public class ShoppingCartController {
	
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
	public String addcart (@RequestParam(value="movieId", required=false) String movieId, 
			@RequestParam(value="name", required=false) String name, 
			@RequestParam(value="quantity", required=false) String quantity 
			, HttpServletRequest request)
	{
		//check for correct parameter later.. maybe not necessary 
		ShoppingCart newItem = new ShoppingCart(Integer.parseInt(movieId), Integer.parseInt(quantity), name);
		
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
	
	
}
