package com.bank.account;

import com.bank.constants.TimeInterval;
import com.bank.models.BankAccount;
import com.bank.models.Transaction;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TransactionService {
    /**
     * Retrieves a map where each {@code BankAccount} is associated with a list of transactions (transfers and payments)
     * with negative amounts.
     *
     * @param accounts The list of {@code BankAccount}s to process.
     * @return A map where each {@code BankAccount} is associated with a list of transactions with negative amounts.
     */
    public static Map<BankAccount, List<Transaction>> getExcludePositiveTransactionsAccounts(List<BankAccount> accounts) {
        return accounts.stream()
                .filter(account -> account.getTransfers().stream().anyMatch(tx -> tx.getAmount() < 0)
                        || account.getPayments().stream().anyMatch(tx -> tx.getAmount() < 0))
                .collect(Collectors.toMap(
                        account -> account,
                        account -> Stream.concat(account.getTransfers().stream(), account.getPayments().stream())
                                .filter(tx -> tx.getAmount() < 0)
                                .collect(Collectors.toList())
                ));
    }

    /**
     * Calculates the balance of a {@code BankAccount} by summing up the amounts of all associated transactions
     * (both payments and transfers).
     *
     * @param account The {@code BankAccount} for which to calculate the balance.
     * @return The balance of the {@code BankAccount} based on the sum of all associated transaction amounts.
     */
    public static Double getAccountBalance(BankAccount account) {
        return Stream.of(account)
                .flatMap(a -> Stream.concat(a.getPayments().stream(), a.getTransfers().stream()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * Calculates the Time Interval of {@code BankAccount} by checking subsequent given transaction type.
     *
     * @param account The {@code BankAccount} for which to calculate the Possible Time Intervals.
     * @param transactionText The {@code Transaction#text} for which to calculate Time Intervals.
     * @return The List of the {@code TimeInterval} based on transactions of the given type.
     */
    public static List<TimeInterval> getAccountTransactionTimeInterval(BankAccount account, String transactionText) {
        List<TimeInterval> timeIntervals = new ArrayList<>();
        List<Transaction> filteredTxs = Stream.of(account)
                .flatMap(a -> Stream.concat(a.getPayments().stream(), a.getTransfers().stream()))
                .filter(tx -> tx.getText().equalsIgnoreCase(transactionText))
                .sorted(Comparator.comparing(Transaction::getTransactionDate))
                .collect(Collectors.toList());

        if (filteredTxs.size() < 2) {
            timeIntervals.add(TimeInterval.OTHER);
        }

        for (int i = 0; i < filteredTxs.size(); i++) {
            if ((i + 1) < filteredTxs.size()) {
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
