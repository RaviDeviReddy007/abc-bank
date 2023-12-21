package com.bank.account;

import com.bank.constants.TimeInterval;
import com.bank.constants.TransactionType;
import com.bank.models.BankAccount;
import com.bank.models.Payment;
import com.bank.models.Transaction;
import com.bank.models.Transfer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TransactionServiceTest {

	@DisplayName("Test Exclude Positive Transactions Accounts")
	@Test
	void testExcludePositiveAmounts() {
		Transfer transfer1 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 17), "T-11 streaming", TransactionType.TRANSFER, -100);
		Payment payment1 = new Payment(LocalDate.of(2023, Month.DECEMBER, 18), "T-12 gym", TransactionType.PAYMENT, -200, "Recipient-1");
		
		Transfer transfer2 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 19), "T-21 salary", TransactionType.TRANSFER, 1000);
		Payment payment2 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-22 gym", TransactionType.PAYMENT, -100, "Recipient-2");

		Transfer transfer3 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 19), "T-31 salary", TransactionType.TRANSFER, 1000);
		Payment payment3 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-32 gym", TransactionType.PAYMENT, 100, "Recipient-3");

		BankAccount account1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(transfer1), List.of(payment1));
		BankAccount account2 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(transfer2), List.of(payment2));
		BankAccount account3 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(transfer3), List.of(payment3));

		Map<BankAccount, List<Transaction>> expectedOutput = Map.of(account1, List.of(transfer1, payment1), account2, List.of(payment2));
		assertThat(TransactionService.getExcludePositiveTransactionsAccounts(List.of(account1, account2, account3)), is(expectedOutput));
	}
	
	@DisplayName("Test Account Balance Amount")
	@Test
	void testAccountBalance() {
		Transfer transfer1 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 14), "T-11 streaming", TransactionType.TRANSFER, -100);
		Payment payment1 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-12 gym", TransactionType.PAYMENT, -200, "Recipient-1");
		Transfer transfer2 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 16), "T-21 salary", TransactionType.TRANSFER, 1000);
		Payment payment2 = new Payment(LocalDate.of(2023, Month.DECEMBER, 17), "T-22 gym", TransactionType.PAYMENT, -100, "Recipient-2");

		BankAccount account1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(transfer1, transfer2), List.of(payment1, payment2));
		assertEquals(600, TransactionService.getAccountBalance(account1));
	}

	
	@DisplayName("Test Time Intervals of transaction types")
	@Test
	void testAccountTransactionTimeInterval() {
		Transfer transfer1 = new Transfer(LocalDate.of(2023, Month.MAY, 23), "Video Streaming", TransactionType.TRANSFER, -99);
		Payment payment1 = new Payment(LocalDate.of(2023, Month.JUNE, 20), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Transfer transfer2 = new Transfer(LocalDate.of(2023, Month.JUNE, 22), "Video Streaming", TransactionType.TRANSFER, -99);
		Transfer transfer3 = new Transfer(LocalDate.of(2023, Month.JUNE, 25), "Salary", TransactionType.TRANSFER, 1000);
		Payment payment2 = new Payment(LocalDate.of(2023, Month.JUNE, 28), "Gym", TransactionType.PAYMENT, -50, "123-456");
		Payment payment3 = new Payment(LocalDate.of(2023, Month.JULY, 4), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Payment payment4 = new Payment(LocalDate.of(2023, Month.JULY, 18), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Transfer transfer4 = new Transfer(LocalDate.of(2023, Month.JULY, 23), "Video Streaming", TransactionType.TRANSFER, -99);
		Payment payment5 = new Payment(LocalDate.of(2023, Month.AUGUST, 1), "Gym", TransactionType.PAYMENT, -200, "123-456");

		BankAccount account1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(transfer1, transfer2, transfer3, transfer4), List.of(payment1, payment2, payment3, payment4, payment5));
		
		assertEquals(1, TransactionService.getAccountTransactionTimeInterval(account1, "Video Streaming").size());
		assertEquals(TimeInterval.MONTHLY, TransactionService.getAccountTransactionTimeInterval(account1, "Video Streaming").get(0));
		assertNotEquals(1, TransactionService.getAccountTransactionTimeInterval(account1, "Gym").size());
		assertNotEquals(TimeInterval.BIWEEKLY, TransactionService.getAccountTransactionTimeInterval(account1, "Gym").get(0));
	}
}
