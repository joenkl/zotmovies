package com.spring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.spring.model.ShoppingCart;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;


@Controller
@RequestMapping(value="/shopping-cart")
public class ShoppingCartController {
	
	@RequestMapping(value="/sp-form")
	public String indexSP (ModelMap cart)
	{
		cart.put("cart", new ShoppingCart());
		return "cart";
	}
	
	@RequestMapping(value="/addcart", method=RequestMethod.POST)
	public String addcart (@ModelAttribute("shoppingCart") ShoppingCart newItem, HttpSession session)
	{
		List<ShoppingCart> shoppingCartList=(List<ShoppingCart>) session.getAttribute("shoppingCart");
		
		if(shoppingCartList == null)
		{
			shoppingCartList = new ArrayList<>();
			shoppingCartList.add(newItem);
		}
		else
		{
			boolean isDuplicate = false;
			
			for(ShoppingCart cartItem : shoppingCartList){
				if (newItem.getID() == cartItem.getID())
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
