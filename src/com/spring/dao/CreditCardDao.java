package com.spring.dao;

import com.spring.model.CreditCard;
public interface CreditCardDao {

	public Boolean isCorrectCard(String cardNumber, String fName, String lName, String expDate);
}
