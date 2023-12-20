package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TransactionType;

public class Transaction {
	LocalDate transactionDate;
	String text;
	TransactionType transactionType;
	double amount;
	
	public Transaction() {
		super();
	}

	public Transaction(LocalDate transactionDate, String text, TransactionType transactionType, double amount) {
		super();
		this.transactionDate = transactionDate;
		this.text = text;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
