package com.bank.models;

import java.util.List;
import java.util.UUID;

public class Account {

	UUID idetentifier;
	UUID userIdentifier;
	List<Transfer> transfers;
	List<Payment> payments;
	
	
	public Account(UUID idetentifier, UUID userIdentifier, List<Transfer> transfers, List<Payment> payments) {
		super();
		this.idetentifier = idetentifier;
		this.userIdentifier = userIdentifier;
		this.transfers = transfers;
		this.payments = payments;
	}
	
	public UUID getIdetentifier() {
		return idetentifier;
	}
	public void setIdetentifier(UUID idetentifier) {
		this.idetentifier = idetentifier;
	}
	public UUID getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(UUID userIdentifier) {
		this.userIdentifier = userIdentifier;
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
