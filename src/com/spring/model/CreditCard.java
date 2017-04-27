package com.spring.model;

import java.util.Date;

public class CreditCard {
	String id;
	String first_name;
	String last_name;
	Date expiration;
	public CreditCard(String id, String first_name, String last_name, Date expiration) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.expiration = expiration;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	} 

	
}
