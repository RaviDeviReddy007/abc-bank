# abc bank

This project provides methods to analyze and process bank account transactions.

## Methods

### `getExcludePositiveTransactionsAccounts(List<BankAccount> accounts)`

Retrieves a map where each `BankAccount` is associated with a list of transactions (transfers and payments) with negative amounts.

### `getAccountBalance(BankAccount account)`

Retrieves total balance of the give account

### `getAccountTransactionTimeInterval(BankAccount account, String transactionText)`

Retrieves the possible Time Intervals(Weekly, Monthly, BiWeekly) of the given account and transaction type(e.g. gym etc.)

### Assumptions
Task#3 - Just providing the account and transactionType to see the possible interval types.
There is some confusion around how to show the data types because for the give transaction text there will be different Time Intervals and there is other alternative to retrieve boolean to show weather 
given Time Intervals is matching for the given account and type/text.