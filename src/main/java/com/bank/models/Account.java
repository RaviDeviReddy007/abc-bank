package com.bank.models;

import java.util.UUID;

/**
 * The {@code Account} class represents a user account of either Bank Account or Credit Card.
 * Each account is uniquely identified by a {@link UUID}.
 */
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
