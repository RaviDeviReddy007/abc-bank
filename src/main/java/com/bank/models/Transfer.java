package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TransactionType;

public class Transfer extends Transaction{

	public Transfer() {
		super();
	}

	public Transfer(LocalDate transactionDate, String text, TransactionType transactionType, double amount) {
		super(transactionDate, text, transactionType, amount);
	}

}
