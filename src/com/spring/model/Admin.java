package com.spring.model;

public class Admin {
	String email;
	String password;
	String fullName;
	
	public Admin (String email, String password, String fullName)
	{
		super();
		this.email = email;
		this.password = password;
		this.fullName = fullName;
	}
	
	public String getAdminEmail(){
		return email;
	}
	
	public void setAdminEmail(String email){
		this.email = email;
	}
	
	public String getAdminPassword (){
		return password;
	}
	
	public void setAdminPassword(String password){
		this.password = password;
	}
	
	public String getAdminFullName(){
		return fullName;
	}
	
	public void setAdminFullName(String fullName){
		this.fullName = fullName;
	}
}
