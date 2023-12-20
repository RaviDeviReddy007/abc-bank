package com.bank.models;

import java.util.List;
import java.util.UUID;

public class BankAccount extends Account{
	UUID idetentifier;
	List<Transfer> transfers;
	List<Payment> payments;
	
	public BankAccount(UUID idetentifier, UUID userIdentifier, List<Transfer> transfers, List<Payment> payments) {
		super(userIdentifier);
		this.idetentifier = idetentifier;
		this.transfers = transfers;
		this.payments = payments;
	}
	
	public UUID getIdetentifier() {
		return idetentifier;
	}
	public void setIdetentifier(UUID idetentifier) {
		this.idetentifier = idetentifier;
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
