package com.drronidz.chapter_5.d_reducing;

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
        /* Reducing */
        // summing the elements (default with initial value)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int defaultSum = 0;
        for (int x: numbers) {
            defaultSum += x;
        }
        // No initial value
        Optional<Integer> NoInitialValueSum = numbers.stream().reduce((a,b) -> (a+b));

        // Maximum & minimum
        Optional<Integer> max = numbers.stream().reduce(Integer::max);
        Optional<Integer> maxWithLambda = numbers.stream().reduce((a,b) -> (a > b ? a : b));
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        Optional<Integer> minWithLambda = numbers.stream().reduce((a,b) -> (a < b ? a : b));
    }
}
