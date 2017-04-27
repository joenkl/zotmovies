package com.spring.dao;

import java.util.List;

import com.spring.model.Sale;
public interface SaleDao {
	public void addOrder(int movieID, int cusID);
	public List<Sale> getLatestOrder(int numOfOrder);
}
