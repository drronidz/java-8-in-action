package com.drronidz.chapter_4.e_stream_operations;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/24/2023 2:16 PM
*/

import com.drronidz.chapter_4.Dish;

import java.util.*;
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
        /* Stream operations */
        // 1/ Get a stream from the list of dishes
        // 2/ intermediate operation (filter)
        // 3/ intermediate operation (map)
        // 4/ intermediate operation (limit)
        // 5/ Convert the Stream into a List
        List<String> namesThree = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        // Intermediate operations
        // Printing the dishes as they're filtered
        // Printing the dishes as you extract their names
        List<String> namesFour = menu.stream()
                .filter(dish -> {
                    System.out.println("Filtering" + dish.getName());
                    return dish.getCalories() > 300;
                })
                .map(dish -> {
                    System.out.println("Mapping" + dish.getName());
                    return dish.getName();
                })
                .limit(3)
                .collect(toList());

        // Terminal operations
        menu.stream().forEach(System.out::println);
    }
}
