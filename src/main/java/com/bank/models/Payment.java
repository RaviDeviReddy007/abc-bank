package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TrasactionType;

public class Payment extends Transaction {
	
	String recipientNumber;

	public String getRecipientNumber() {
		return recipientNumber;
	}

	public void setRecipientNumber(String recipientNumber) {
		this.recipientNumber = recipientNumber;
	}

	public Payment(LocalDate transactionDate, String text, TrasactionType trasactionType, double amount, String recipientNumber) {
		super(transactionDate, text, trasactionType, amount);
		this.recipientNumber = recipientNumber;
	}

	
	
	
}
