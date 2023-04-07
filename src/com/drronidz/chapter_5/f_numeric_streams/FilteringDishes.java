package com.drronidz.chapter_5.f_numeric_streams;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/7/2023 6:34 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.List;

import static java.util.Arrays.asList;

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
        /* Numeric streams */
        // Calculating the number of calories in the menu
        // with boxing cost (From Integer to it before performing the summation)
        int caloriesOne = menu.stream()
                .map(Dish::getCalories)
                .reduce(Integer::sum)
                .get();

        // Mapping to a numeric stream
        // Returns a Stream<Dish>
        // Returns a IntStream
        int caloriesTwo = menu
                .stream()
                .mapToInt(Dish::getCalories)
                .sum();
    }
}
