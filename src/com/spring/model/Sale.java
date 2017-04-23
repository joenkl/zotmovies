package com.spring.model;

import java.util.Date;;

public class Sale {
	int id;
	int customer_id;
	int movie_id;
	Date sale_date;
	public Sale(int id, int customer_id, int movie_id, Date sale_date) {
		super();
		this.id = id;
		this.customer_id = customer_id;
		this.movie_id = movie_id;
		this.sale_date = sale_date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	
	
	
}
