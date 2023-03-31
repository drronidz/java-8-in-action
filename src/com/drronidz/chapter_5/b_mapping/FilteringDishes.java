package com.drronidz.chapter_5.b_mapping;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/26/2023 7:44 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.Arrays;
import java.util.List;
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
        /* Mapping (for example, in SQL you can select a particular column from a table)*/
        // Applying a function to each element of a stream
        // extracting the names of the dishes in the stream
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());

        // return a list of the number of characters for each word from a list
        List<String> words = Arrays.asList("Java8","Lambdas", "In", "Action");
        List<Integer> wordsLength = words.stream()
                .map(String::length)
                .collect(toList());

        // Flattening streams (Type of stream is String[] !)
        // Incorrect use of map to find unique characters from a list of words
        words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(toList());

        // Attempt using map and Arrays.stream
        String [] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

        // Convert each word into an array of its individual letters
        // Makes each array into a separate stream
        // Type of stream is Stream<Stream <String>>
        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(toList());

        // Using flatMap
        // Converts each word into an array of its individual letters
        // Flattens each generated stream into a single stream
        List<String> uniqueCharacters =
                words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(toList());

        // Quiz 1 (Mapping)
        // From : [1, 2, 3, 4, 5] To -> [1, 4, 9, 16, 25]
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers.stream()
                .map(number -> number * number)
                .collect(toList());

        // Quiz 2 (Mapping)
        // From : [1, 2, 3] && [4, 5] --> [(1,4),(1,5),(2,4),(2,5),(3,4),(3,5)]
        List<Integer> numbersOne = Arrays.asList(1, 2, 3);
        List<Integer> numbersTwo = Arrays.asList(4, 5);
        List<int[]> pairs =
                numbersOne.stream()
                .flatMap(i -> numbersTwo.stream().map(j -> new int []{i,j}))
                .collect(toList());
    }
}
