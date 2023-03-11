package com.drronidz.chapter_3;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/11/2023 3:57 PM
*/


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class FilteringTomato {
    public static void main(String[] args) {

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
}
