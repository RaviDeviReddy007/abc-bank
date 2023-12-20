package com.bank.models;

import java.util.UUID;

public class CreditCard extends Transaction{

	
	Long cardNumber;
	UUID userIdentifier;
	
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public UUID getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(UUID userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	
	
}
