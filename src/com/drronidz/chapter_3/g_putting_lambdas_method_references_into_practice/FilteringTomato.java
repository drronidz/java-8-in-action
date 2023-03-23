package com.drronidz.chapter_3.g_putting_lambdas_method_references_into_practice;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/23/2023 5:28 PM
*/

import com.drronidz.chapter_3.Tomato;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;

/** Putting lambdas and method references into practice! **/

// Step 1: Pass code
class TomatoComparator implements Comparator<Tomato> {
    @Override
    public int compare(Tomato tomatoOne, Tomato tomatoTwo) {
        return tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());
    }
}

public class FilteringTomato {

    public static void main(String[] args) {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(190, "red"),
                new Tomato(150, "yellow"),
                new Tomato(180, "green"),
                new Tomato(110, "purple")
        );

        // Step 1: Pass code
        inventory.sort(new TomatoComparator());

        // Step 2: use an anonymous class
        inventory.sort(new Comparator<Tomato>() {
            @Override
            public int compare(Tomato o1, Tomato o2) {
                return o1.getWeight().compareTo(o2.getWeight());
            }
        });

        // Step 3: use lambda expression
        inventory.sort((o1, o2) -> o1.getWeight().compareTo(o2.getWeight()));

        Comparator<Tomato> tomatoComparatorOne = Comparator.comparing((Tomato tomato) -> tomato.getWeight());

        Comparator<Tomato> tomatoComparatorTwo = comparing((tomato) -> tomato.getWeight());

        // Final solution
        inventory.sort(comparing(Tomato::getWeight));
    }
}
