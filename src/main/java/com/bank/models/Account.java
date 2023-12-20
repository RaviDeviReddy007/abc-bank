package com.bank.models;

import java.util.UUID;

public class Account {
	UUID userIdentifier;
	
	public Account(UUID userIdentifier) {
		super();
		this.userIdentifier = userIdentifier;
	}

	public UUID getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(UUID userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	
	
}
