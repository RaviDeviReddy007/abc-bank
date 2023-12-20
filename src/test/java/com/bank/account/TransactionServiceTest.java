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
		Transfer t11 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 17), "T-11 streaming", TransactionType.TRANSFER, -100);
		Payment t12 = new Payment(LocalDate.of(2023, Month.DECEMBER, 18), "T-12 gym", TransactionType.PAYMENT, -200, "Recipient-1");
		
		Transfer t21 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 19), "T-21 salary", TransactionType.TRANSFER, 1000);
		Payment t22 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-22 gym", TransactionType.PAYMENT, -100, "Recipient-2");

		Transfer t31 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 19), "T-31 salary", TransactionType.TRANSFER, 1000);
		Payment t32 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-32 gym", TransactionType.PAYMENT, 100, "Recipient-3");

		BankAccount a1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(t11), List.of(t12));
		BankAccount a2 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(t21), List.of(t22));
		BankAccount a3 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(t31), List.of(t32));

		Map<BankAccount, List<Transaction>> expectedOutput = Map.of(a1, List.of(t11, t12), a2, List.of(t22));
		assertThat(TransactionService.getExcludePositiveTransactionsAccounts(List.of(a1, a2, a3)), is(expectedOutput));
	}
	
	@DisplayName("Test Account Balance Amount")
	@Test
	void testAccountBalance() {
		Transfer t1 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 14), "T-11 streaming", TransactionType.TRANSFER, -100);
		Payment p1 = new Payment(LocalDate.of(2023, Month.DECEMBER, 15), "T-12 gym", TransactionType.PAYMENT, -200, "Recipient-1");
		Transfer t2 = new Transfer(LocalDate.of(2023, Month.DECEMBER, 16), "T-21 salary", TransactionType.TRANSFER, 1000);
		Payment p2 = new Payment(LocalDate.of(2023, Month.DECEMBER, 17), "T-22 gym", TransactionType.PAYMENT, -100, "Recipient-2");

		BankAccount a1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(t1, t2), List.of(p1, p2));
		assertEquals(600, TransactionService.getAccountBalance(a1));
	}

	
	@DisplayName("Test Time Intervals of transaction types")
	@Test
	void testAccountTransactionTimeInterval() {
		Transfer t1 = new Transfer(LocalDate.of(2023, Month.MAY, 23), "Video Streaming", TransactionType.TRANSFER, -99);
		Payment p1 = new Payment(LocalDate.of(2023, Month.JUNE, 20), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Transfer t2 = new Transfer(LocalDate.of(2023, Month.JUNE, 22), "Video Streaming", TransactionType.TRANSFER, -99);
		Transfer t3 = new Transfer(LocalDate.of(2023, Month.JUNE, 25), "Salary", TransactionType.TRANSFER, 1000);
		Payment p2 = new Payment(LocalDate.of(2023, Month.JUNE, 28), "Gym", TransactionType.PAYMENT, -50, "123-456");
		Payment p3 = new Payment(LocalDate.of(2023, Month.JULY, 4), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Payment p4 = new Payment(LocalDate.of(2023, Month.JULY, 18), "Gym", TransactionType.PAYMENT, -200, "123-456");
		Transfer t4 = new Transfer(LocalDate.of(2023, Month.JULY, 23), "Video Streaming", TransactionType.TRANSFER, -99);
		Payment p5 = new Payment(LocalDate.of(2023, Month.AUGUST, 1), "Gym", TransactionType.PAYMENT, -200, "123-456");

		BankAccount a1 = new BankAccount(UUID.randomUUID(), UUID.randomUUID(), List.of(t1, t2, t3, t4), List.of(p1, p2, p3, p4, p5));
		
		assertEquals(1, TransactionService.getAccountTransactionTimeInterval(a1, "Video Streaming").size());
		assertEquals(TimeInterval.MONTHLY, TransactionService.getAccountTransactionTimeInterval(a1, "Video Streaming").get(0));
		assertNotEquals(1, TransactionService.getAccountTransactionTimeInterval(a1, "Gym").size());
		assertNotEquals(TimeInterval.BIWEEKLY, TransactionService.getAccountTransactionTimeInterval(a1, "Gym").get(0));
	}

}
