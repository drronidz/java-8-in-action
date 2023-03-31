package com.drronidz.chapter_5.c_finding_and_matching;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/26/2023 7:44 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class FilteringDishes {

    public static final List<Dish> menu = asList(
            new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 400, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH)
    );

    public static void main(String[] args) {
        /* Finding and matching */
        // Checking to see if a predicate matches at least one element
        if(menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly");
        }

        // Checking to see if a predicate matches all elements
        boolean isHealthy;
        isHealthy = menu.stream()
                .allMatch(dish -> dish.getCalories() < 1000);
        // noneMatch
        isHealthy = menu.stream()
                .noneMatch(dish -> dish.getCalories() >= 1000);

        // Finding an element
        Optional<Dish> dish =
                menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();

        // Optional in a nutshell
        // Returns an Optional<Dish>
        // If a value is contained, it's printed, otherwise nothing happens
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));
    }
}
