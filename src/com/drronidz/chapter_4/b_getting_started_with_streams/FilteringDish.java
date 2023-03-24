package com.drronidz.chapter_4.b_getting_started_with_streams;

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
        // 1/ Get a stream from men (the list of dishes)
        // 2/ Create a pipeline of operations: first filter high-calorie dishes
        // 3/ Get the names of the dishes
        // 4/ Select only the first three
        // 5/ Store the result in another List
        // 6/ The result is [pork, beef, chicken]
        List<String> threeHighCaloricDishNames =
                menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .limit(3)
                .collect(toList());

        /* Streams vs Collections */
        // throw an exception indicating the stream has been consumed (Traversable only once)
        List<String> title = Arrays.asList("Java8", "in", "Action");
        Stream<String> stringStream = title.stream();
        stringStream.forEach(System.out::println);
        stringStream.forEach(System.out::println);
    }
}
