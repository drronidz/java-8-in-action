package com.drronidz.chapter_4.a_what_are_streams;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/24/2023 2:16 PM
*/

import com.drronidz.chapter_4.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class FilteringDish {
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

    public static final Map<String, List<String>> dishTags = new HashMap<>();

    static {
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));
    }
    public static void main(String[] args) {

        // Filter the elements using an accumulator (Before Java 7)
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish: menu) {
            if (dish.getCalories() <400) {
                lowCaloricDishes.add(dish);
            }
        }

        // Sort the dishes with an anonymous class
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        // Process the sorted list to select the names of dishes
        List<String> lowCaloricDishesNameOld = new ArrayList<>();
        for (Dish dish: lowCaloricDishes) {
            lowCaloricDishesNameOld.add(dish.getName());
        }

        // After Java 8
        // Select dishes that are below 400 calories
        // Sort them by calories
        // Extract the names of these dishes
        // Store all the names in a List
        List<String> lowCaloricDishesNameNew = menu.stream()
                .filter(dish -> dish.getCalories() <400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        // Exploit a multicore architecture & execute this code in parallel
        List<String> lowCaloricDishesName = menu.parallelStream()
                .filter(dish -> dish.getCalories() < 400)
                .sorted(comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());

        // We don't worry about parallelize certain data processing tasks (threads and locks) -> Stream API does it !
        Map<Dish.Type, List<Dish>> dishesByType =
                menu.stream().collect(groupingBy(Dish::getType));
    }
}
