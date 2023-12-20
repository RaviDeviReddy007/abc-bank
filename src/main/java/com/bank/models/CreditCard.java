package com.bank.models;

import java.util.List;
import java.util.UUID;

public class CreditCard extends Account {
	Long cardNumber;
	List<Transaction> transactions;
	
	public CreditCard(UUID userIdentifier, Long cardNumber, List<Transaction> transactions) {
		super(userIdentifier);
		this.cardNumber = cardNumber;
		this.transactions = transactions;
	}

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	

	
	
}
