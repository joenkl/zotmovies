package com.spring.model;

public class ShoppingCart {
	
	int movieId;
	int quantity;
	String movieTitle;
	
	public ShoppingCart()
	{
		movieId = -1;
		quantity = -1;
	}
	
	public ShoppingCart(int movieId, int quantity, String movieTitle) {
		super();
		this.movieId = movieId;
		this.quantity = quantity;
		this.movieTitle = movieTitle;
	}
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieId(int movieId) {
		this.movieId = movieId;
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
