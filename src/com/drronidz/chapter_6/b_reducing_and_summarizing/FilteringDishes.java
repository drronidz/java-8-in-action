package com.drronidz.chapter_6.b_reducing_and_summarizing;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/14/2023 6:24 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class FilteringDishes {

    /** Reducing and summarizing **/
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
        // Counting how many dishes
        long howManyDishes = menu.stream().collect(Collectors.counting());

        // Far more directly
        long howManyDishesOne = menu.stream().count();

        // Finding max and min in a stream of values
        Comparator<Dish> dishCaloriesComparator =
                Comparator.comparingInt(Dish::getCalories);

        Optional<Dish> maxCalorieDish = menu
                .stream()
                .collect(maxBy(dishCaloriesComparator));

        Optional<Dish> minCalorieDish = menu
                .stream()
                .collect(minBy(dishCaloriesComparator));

        // Summarization
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        // Averaging
        double averageCalories = menu.stream().collect(averagingInt(Dish::getCalories));

        // Summary statistics
        IntSummaryStatistics menuStatistics =
                menu.stream().collect(summarizingInt(Dish::getCalories));

        // Joining Strings (non readable)
        String shortMenuOne = menu.stream().map(Dish::getName).collect(joining());

        // Joining Strings (readable)
        String shortMenuReadable = menu.stream().map(Dish::getName).collect(joining(","));

        // Generalized summarization with reduction
        int totalCaloriesTwo = menu.stream().collect(reducing(0, Dish::getCalories, (i,j) -> i + j));

        // Highest-calorie dish using the one-argument version of reducing as follows
        Optional<Dish> mostCalorieDish = menu
                .stream()
                .collect(reducing((dishOne, dishTwo) -> dishOne.getCalories() >
                        dishTwo.getCalories() ? dishOne : dishTwo));

        // Collect vs Reduce
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> numbers = stream.reduce(
                new ArrayList<>(),
                (List<Integer> l, Integer e) ->
                { l.add(e);
                return l;},
                (List<Integer> l1, List<Integer> l2) -> {
                    l1.addAll(l2);
                    return l1;
                }
        );

        // Collection framework flexibility: doing the same operation in different ways
        // Initial value
        // Transformation function
        // Aggregating function
        int totalCaloriesThree = menu
                .stream()
                .collect(reducing(0, Dish::getCalories, Integer::sum));

    }
}
