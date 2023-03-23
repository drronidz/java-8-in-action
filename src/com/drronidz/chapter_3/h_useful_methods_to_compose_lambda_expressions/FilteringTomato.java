package com.drronidz.chapter_3.h_useful_methods_to_compose_lambda_expressions;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/23/2023 5:47 PM
*/

import com.drronidz.chapter_3.Tomato;

import java.util.Comparator;

/** useful methods to compose lambda expressions **/
public class FilteringTomato {
    public static void main(String[] args) {
        // Composing Comparators
        Comparator<Tomato> comparator = Comparator.comparing(Tomato::getWeight);
    }
}
