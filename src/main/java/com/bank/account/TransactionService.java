package com.bank.account;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.bank.constants.TimeInterval;
import com.bank.models.BankAccount;
import com.bank.models.Transaction;

public class TransactionService {

	public static List<Transaction> getExcludePositiveAmounts(List<BankAccount> accounts) {
		return accounts.stream()
				.flatMap(account -> Stream.concat(account.getPayments().stream(), account.getTransfers().stream()))
				.filter(tx -> tx.getAmount() < 0)
				.collect(Collectors.toList());
	}

	public static Double getAccountBalanace(BankAccount account) {
		return Stream.of(account).flatMap(a -> Stream.concat(a.getPayments().stream(), a.getTransfers().stream()))
				.mapToDouble(tx -> tx.getAmount())
				.sum();
	}

	public static List<TimeInterval> getAccountTransactionTimeInterval(BankAccount account, String transactionText) {
		List<TimeInterval> timeIntervals = new ArrayList<TimeInterval>();
		List<Transaction> filteredTxs = Stream.of(account)
				.flatMap(a -> Stream.concat(a.getPayments().stream(), a.getTransfers().stream()))
				.filter(tx -> tx.getText().equalsIgnoreCase(transactionText))
				.sorted((tx1, tx2) -> tx1.getTransactionDate().compareTo(tx2.getTransactionDate()))
				.collect(Collectors.toList());

		if (filteredTxs.size() < 2) {
			timeIntervals.add(TimeInterval.OTHER);
		}

		for (int i = 0; i < filteredTxs.size(); i++) {
			if (i + 1 < filteredTxs.size()) {
				timeIntervals.add(calculateTimeInterval(filteredTxs.get(i), filteredTxs.get(i + 1)));
			}
		}

		return timeIntervals.stream().distinct().collect(Collectors.toList());
	}

	public static TimeInterval calculateTimeInterval(Transaction t1, Transaction t2) {
		long daysDuration = calculateDaysGap(t1.getTransactionDate(), t2.getTransactionDate());

		if (daysDuration == 1) {
			return TimeInterval.DAILY;
		} else if (daysDuration == 7) {
			return TimeInterval.WEEKLY;
		} else if (daysDuration == 14) {
			return TimeInterval.BIWEEKLY;
		} else if (daysDuration >= 28 && daysDuration <= 31) {
			return TimeInterval.MONTHLY;
		}
		return TimeInterval.OTHER;
	}

	private static long calculateDaysGap(LocalDate date1, LocalDate date2) {
		return ChronoUnit.DAYS.between(date1, date2);
	}
}
