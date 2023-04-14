package com.drronidz.chapter_6.a_collectors_in_a_nutshell;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/14/2023 5:50 PM
*/

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;



public class GroupingTransactions {

    /** Grouping transactions by currency in imperative style **/
    public static List<Transaction> transactions = Arrays.asList( new Transaction(Currency.EUR, 1500.0),
            new Transaction(Currency.USD, 2300.0),
            new Transaction(Currency.GBP, 9900.0),
            new Transaction(Currency.EUR, 1100.0),
            new Transaction(Currency.JPY, 7800.0),
            new Transaction(Currency.CHF, 6700.0),
            new Transaction(Currency.EUR, 5600.0),
            new Transaction(Currency.USD, 4500.0),
            new Transaction(Currency.CHF, 3400.0),
            new Transaction(Currency.GBP, 3200.0),
            new Transaction(Currency.USD, 4600.0),
            new Transaction(Currency.JPY, 5700.0),
            new Transaction(Currency.EUR, 6800.0)
    );

    public static void main(String[] args) {
        groupTransactionFunctional();

        /* Collectors as advanced reductions */

    }



    // Create the Map where the grouped transaction will be accumulated
    // Iterate the List of Transactions
    // If there's no entry in the grouping Map for this currency, create it
    // Extract he Transaction's currency
    // Add the currently traversed Transaction to the List of Transactions with the same currency
    private static void groupTransactionImperative() {
        Map<Currency, List<Transaction>> transactionByCurrencies = new HashMap<>();
        for (Transaction transaction: transactions) {
            Currency currency = transaction.getCurrency();
            List<Transaction> transactionsForCurrency = transactionByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
    }

    private static void groupTransactionFunctional() {
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions
                .stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println(transactionsByCurrencies);
    }

    private static void collectingAdvancedReduction() {
        List<Transaction> transactionsList = GroupingTransactions.transactions
                .stream()
                .collect(Collectors.toList());
    }
}
