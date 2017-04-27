package com.spring.dao;

import com.spring.model.Customer;

public interface CustomerDao {
	public Boolean isCustomer(String email, String password);
	public Customer getCustomer(int id);
	public Customer getCustomerInfo(String email, String password);
}
