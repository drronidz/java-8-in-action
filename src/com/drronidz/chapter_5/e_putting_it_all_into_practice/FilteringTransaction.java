package com.drronidz.chapter_5.e_putting_it_all_into_practice;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/7/2023 3:59 PM
*/

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilteringTransaction {
    public static void main(String[] args) {

        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        /* Finding all transactions in the year 2011 and sort them by value (small to high) */
        // Passing a predicate to filter to select transaction in year 2011.
        // Sort them by using the value of the transactions
        // Collect all the elements of the resulting Stream into a List
        List<Transaction> transactionList2011 = transactions
                .stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        /* Finding the unique cites where the traders work */
        // Extract the city from each trader associated with the transaction
        // Select only unique cities
        List<String> cites = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        /* Find all traders from Cambridge and sort them by name */
        // Extract all traders from the transactions
        // Select only the traders from Cambridge
        // Make sure you don't have any duplicates
        // Sort the resulting stream of traders by their names
        List<Trader> traders = transactions
                .stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity() == "Cambridge")
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        /* Returning a string of all traders' names sorted alphabetically */
        // Extract all the names of the traders as a Stream of Strings
        // Select only the unique names
        // Sort the names alphabetically
        // Combine each name one by one from a String that concatenates all the names
        String stringOfTraders = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        String stringOfTradersTwo = transactions
                .stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());

        /* Finding any traders based in Milan */
        // Pass a predicate to anyMatch to check if there's a trader from Milan
        boolean milanBased = transactions
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity() == "Milan");

        /* Printing all transactions's values from the traders living in Cambridge */
        // Select the transactions where the traders live in Cambridge
        // Extract the values of these traders
        // Print each value
        transactions
                .stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        /* Finding the highest value of all the transactions */
        // Extract the value of each transaction
        // Calculate the max of the resulting stream
        Optional<Integer> max = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);

        Optional<Integer> maxWithLambda = transactions
                .stream()
                .map(Transaction::getValue)
                .reduce((x,y) -> x > y ? x : y);

        /* Finding the smallest transaction of all the transactions */
        // Find the smallest transaction by repeatedly comparing the values of each transaction

        Optional<Transaction> smallestTransactionOne = transactions
                .stream()
                .reduce((tOne, tTwo) -> tOne.getValue() < tTwo.getValue() ? tOne : tTwo);

        Optional<Transaction> smallestTransactionTwo = transactions
                .stream()
                .min(Comparator.comparing(Transaction::getValue));

    }
}
