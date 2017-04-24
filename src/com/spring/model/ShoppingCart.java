package com.spring.model;

public class ShoppingCart {
	
	int id;
	int quantity;
	String movieTitle;
	
	public int getID(){
		return id;
	}
	public void setID(int newID){
		this.id = newID;
	}
	
	public int getQuantity(){
		return quantity;
	}
	public void setQuantity(int newQuantity){
		this.quantity = newQuantity;
	}
	
	public String getMovieTitle(){
		return movieTitle;
	}
	public void setMovieTitle(String newMovieTitle){
		this.movieTitle = newMovieTitle;
	}
}
