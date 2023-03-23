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

        // Reversed order
        inventory.sort(comparing(Tomato::getWeight).reversed());
    }
}
