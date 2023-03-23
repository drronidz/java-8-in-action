package com.drronidz.chapter_3.h_useful_methods_to_compose_lambda_expressions;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/23/2023 5:47 PM
*/

import com.drronidz.chapter_3.Tomato;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/** useful methods to compose lambda expressions **/
public class FilteringTomato {
    public static void main(String[] args) {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(190, "red"),
                new Tomato(150, "yellow"),
                new Tomato(180, "green"),
                new Tomato(110, "purple")
        );

        // Composing Comparators
        Comparator<Tomato> comparator = comparing(Tomato::getWeight);

        // Reversed order (Storing by decreasing weight)
        inventory.sort(comparing(Tomato::getWeight).reversed());

        // Chaining comparators (Sorting by decreasing weight & further by color when two tomatoes have same weight)
        inventory.sort(comparing(Tomato::getWeight).reversed().thenComparing(Tomato::getColor));

        // Composing Predicates
        // Produces the negation of existing Predicate object redTomato
        Predicate<Tomato> redTomato = tomato -> tomato.getColor().equals("red");
        Predicate<Tomato> notRedTomato = redTomato.negate();

        // Chaining Predicates
        // Chaining tWO predicates (redTomato and heavyTomato to produce redAndHeavyTomato Predicate object)
        Predicate<Tomato> heavyTomato = tomato -> tomato.getWeight() > 150;
        Predicate<Tomato> redAndHeavyTomato = redTomato.and(heavyTomato);

    }
}
