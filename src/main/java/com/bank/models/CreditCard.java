package com.bank.models;

import java.util.List;
import java.util.UUID;

public class CreditCard extends Account {
	Long cardNumber;
	List<Transaction> trasactions;
	
	public CreditCard(UUID userIdentifier, Long cardNumber, List<Transaction> trasactions) {
		super(userIdentifier);
		this.cardNumber = cardNumber;
		this.trasactions = trasactions;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Transaction> getTrasactions() {
		return trasactions;
	}

	public void setTrasactions(List<Transaction> trasactions) {
		this.trasactions = trasactions;
	}
	
	

	
	
}
