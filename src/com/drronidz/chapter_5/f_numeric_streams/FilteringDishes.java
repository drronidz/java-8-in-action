package com.drronidz.chapter_5.f_numeric_streams;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/7/2023 6:34 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

        /* Converting back to a stream of objects */
        // Converting a Stream to a numeric stream
        // Converting the numeric stream to a Stream
        IntStream intStream = menu
                .stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> stream = intStream.boxed();

        /* Default values: OptionalInt */
        OptionalInt maxCalories = menu
                .stream()
                .mapToInt(Dish::getCalories)
                .max();

        // Provide an explicit default maximum if there's no value
        int max = maxCalories.orElse(1);

        /* Numeric ranges */
        // Represents the range [1,100]
        // A stream of even numbers from 1 to 100
        // There are 50 even numbers from 1 to 100
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);

        /* Putting numerical streams into practice : Pythagorean triples */
        // Generating Values
        Stream<int []> pythagoreanTriplesOne =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a,100)
                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                .mapToObj(b -> new int[]{a,b, (int) Math.sqrt(a * a + b * b)}));
        pythagoreanTriplesOne.limit(5)
                .forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        // Produce triples
        // The third element of the tuple must be an integer
        Stream<double []> pythagoreanTriplesTwo =
                IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100).mapToObj(b -> new double[] {a, b, Math.sqrt(a*a + b*b)})
                .filter(t -> t[2] % 1 ==0));
    }
}
