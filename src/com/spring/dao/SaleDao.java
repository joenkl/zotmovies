package com.spring.dao;

import java.text.ParseException;
import java.util.List;

import com.spring.model.Sale;
public interface SaleDao {
	public void addOrder(int cusID, List<Integer> movieIDList) throws ParseException;
	public List<Sale> getLatestOrder(int numOfOrder);
}
