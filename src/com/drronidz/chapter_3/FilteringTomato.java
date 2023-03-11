package com.drronidz.chapter_3;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/11/2023 3:57 PM
*/


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FilteringTomato {
    public static void main(String[] args) throws IOException {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(190, "red"),
                new Tomato(150, "yellow"),
                new Tomato(180, "green"),
                new Tomato(110, "purple"));

        /** Lambda in a nutshell **/

        // Before Lambda expression ...
        Comparator<Tomato> byWeightWithoutLambda = new Comparator<Tomato>() {
            @Override
            public int compare(Tomato tomatoOne, Tomato tomatoTwo) {
                return tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());
            }
        };

        // After Lambda expression ...
        Comparator<Tomato> byWeightWithLambda = (tomatoOne, tomatoTwo) -> tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());

        // After Optimizing
        Comparator<Tomato> byWeightOpt = Comparator.comparing(Tomato::getWeight);

        // Valid Lambda expressions in Java 8
        Predicate<Tomato> predicate = (Tomato tomato) -> tomato.getWeight() > 150;



        /** Where and how to use lambdas **/


        /** Putting lambdas into practice: The execute around pattern **/
        /* Step 4: Pass lambdas */
        String oneLine = processFile((BufferedReader br) -> br.readLine());
        String twoLines = processFile((BufferedReader br) -> br.readLine() + br.readLine());

        /** Using Functional Interfaces **/
        /* Working with Predicate */
        Predicate<Tomato> lightTomatoesPredicate = (Tomato tomato) -> tomato.getWeight() < 150;
        List<Tomato> lightTomatoes = filter(inventory, lightTomatoesPredicate);

        /*Working with Consumer*/
    }

    /** Where and how to use lambdas **/
    /* 3.2.1 Functional Interfaces */
    // Quiz
    public static interface Adder {
        int add(int a,int b);
    }

    public static interface SmartAdder extends Adder {
        int add(double a, double b);
    }

    public static interface Nothing {
    }

    /* 3.2.2 Function descriptor */
    public void process(Runnable runnable) {
        runnable.run();
    }

    Runnable runnableOne = () -> System.out.println("Hello World One 1");

    /** Putting lambdas into practice: The execute around pattern **/
    /* Step 1: */
    public static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
    }
    /* Step 2: Use a functional interface to pass behaviors */
    @FunctionalInterface
    public static interface BufferedReaderProcessor {
        String process(BufferedReader bufferedReader) throws IOException;
    }

    /* Step 3: Execute a behavior !*/
    public static String processFile(BufferedReaderProcessor processor) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return processor.process(br);
        }
    }

    /* Working with a Predicate*/
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for(T s: list) {
            if (predicate.test(s)) {
                results.add(s);
            }
        }
        return results;
    }
}
