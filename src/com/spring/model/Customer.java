package com.spring.model;

public class Customer {
	int id;
	String fist_name;
	String last_name;
	String cc_id;
	String address;
	String email;
	String password;
	public Customer(int id, String fist_name, String last_name, String cc_id, String address, String email,
			String password) {
		super();
		this.id = id;
		this.fist_name = fist_name;
		this.last_name = last_name;
		this.cc_id = cc_id;
		this.address = address;
		this.email = email;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFist_name() {
		return fist_name;
	}
	public void setFist_name(String fist_name) {
		this.fist_name = fist_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCc_id() {
		return cc_id;
	}
	public void setCc_id(String cc_id) {
		this.cc_id = cc_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
	
	

}
