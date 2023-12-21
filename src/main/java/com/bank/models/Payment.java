package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TransactionType;

/**
 * The {@code Payment} class represents payments of the bank account with recipient number as additional to transaction.
 */
public class Payment extends Transaction {
	
	String recipientNumber;

	public String getRecipientNumber() {
		return recipientNumber;
	}

	public void setRecipientNumber(String recipientNumber) {
		this.recipientNumber = recipientNumber;
	}

	public Payment(LocalDate transactionDate, String text, TransactionType transactionType, double amount, String recipientNumber) {
		super(transactionDate, text, transactionType, amount);
		this.recipientNumber = recipientNumber;
	}

	
	
	
}
