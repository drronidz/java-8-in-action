package com.drronidz.chapter_6.b_reducing_and_summarizing;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/14/2023 6:24 PM
*/

import com.drronidz.chapter_5.Dish;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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
    }
}