package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TrasactionType;

public class Transaction {
	
	LocalDate transactionDate;
	String text;
	TrasactionType trasactionType;
	double amount;
	
	
	public Transaction() {
		super();
	}

	public Transaction(LocalDate transactionDate, String text, TrasactionType trasactionType, double amount) {
		super();
		this.transactionDate = transactionDate;
		this.text = text;
		this.trasactionType = trasactionType;
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
	public TrasactionType getTrasactionType() {
		return trasactionType;
	}
	public void setTrasactionType(TrasactionType trasactionType) {
		this.trasactionType = trasactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
