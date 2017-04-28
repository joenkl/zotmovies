package com.spring.dao;

import java.util.List;

import com.spring.model.Sale;
public interface SaleDao {
	public void addOrder(String sql);
	public List<Sale> getLatestOrder(int numOfOrder);
}
