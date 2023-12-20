package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TransactionType;

public class Payment extends Transaction {
	
	String recipientNumber;

	public String getRecipientNumber() {
		return recipientNumber;
	}

	public void setRecipientNumber(String recipientNumber) {
		this.recipientNumber = recipientNumber;
	}

	public Payment(LocalDate transactionDate, String text, TransactionType trasactionType, double amount, String recipientNumber) {
		super(transactionDate, text, trasactionType, amount);
		this.recipientNumber = recipientNumber;
	}

	
	
	
}
