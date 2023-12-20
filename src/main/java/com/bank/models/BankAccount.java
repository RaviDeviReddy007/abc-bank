package com.bank.models;

import java.util.List;
import java.util.UUID;

public class BankAccount extends Account{
	UUID identifier;
	List<Transfer> transfers;
	List<Payment> payments;
	
	public BankAccount(UUID identifier, UUID userIdentifier, List<Transfer> transfers, List<Payment> payments) {
		super(userIdentifier);
		this.identifier = identifier;
		this.transfers = transfers;
		this.payments = payments;
	}
	
	public UUID getIdentifier() {
		return identifier;
	}
	public void setIdentifier(UUID identifier) {
		this.identifier = identifier;
	}
	public List<Transfer> getTransfers() {
		return transfers;
	}
	public void setTransfers(List<Transfer> transfers) {
		this.transfers = transfers;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
}
