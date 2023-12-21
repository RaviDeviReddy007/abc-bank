package com.bank.models;

import java.util.List;
import java.util.UUID;

/**
 * The {@code CreditCard} class represents a user credit card with transactions.
 * Each credit card is uniquely identified by card number.
 * Assumption - here not using the payments as in the given data there is recipient number for card txs.
 */
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
