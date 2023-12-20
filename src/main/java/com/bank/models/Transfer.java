package com.bank.models;

import java.time.LocalDate;

import com.bank.constants.TrasactionType;

public class Transfer extends Transaction{

	public Transfer() {
		super();
	}

	public Transfer(LocalDate transactionDate, String text, TrasactionType trasactionType, double amount) {
		super(transactionDate, text, trasactionType, amount);
	}

}
