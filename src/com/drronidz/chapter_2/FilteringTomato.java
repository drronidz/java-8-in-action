package com.drronidz.chapter_2;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/10/2023 3:39 PM
*/

import com.drronidz.chapter_1.FilteringTomatoes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilteringTomato {
    /*TODO Chapter 2: Passing code with behavior parameterization */
    /** 2.1 Coping with changing requirements **/
    /*  2.1.1 First attempt: filtering green tomatoes */
    public static List<Tomato> filterGreenTomato(List<Tomato> inventory) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato : inventory) {
            if ("green".equals(tomato.getColor())) {
                result.add(tomato);
            }
        }
        return result;
    }
    /*  2.1.2 Second attempt: parameterizing the color & the weight*/

    public static List<Tomato> filterTomatoesByColor(List<Tomato> inventory, String color) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if (tomato.getColor().equals(color)) {
                result.add(tomato);
            }
        }
        return result;
    }

    public static List<Tomato> filterTomatoesByWeight(List<Tomato> inventory, int weight) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if (tomato.getWeight() > weight) {
                result.add(tomato);
            }
        }
        return result;
    }

    /*  2.1.3 Second attempt: filtering with every attribute ou can think of */
    public static List<Tomato> filterTomatoes (List<Tomato> inventory, String color, int weight, boolean flag) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if ((flag && tomato.getColor().equals(color)) || (flag && tomato.getWeight() > weight)) {
                result.add(tomato);
            }
        }
        return result;
    }

    /** Behavior parameterization **/
    /** Anonymous classes **/
    /** Preview of lambda expressions **/
    /** Real-world examples: Comparator, Runnable, and GUI **/

    public static void main(String[] args) {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(150, "green"),
                new Tomato(110, "purple"));

        /** 2.1 Coping with changing requirements **/
        List<Tomato> greenTomatoes = filterTomatoesByColor(inventory, "green");
        List<Tomato> heavyTomatoes = filterTomatoesByWeight(inventory, 150);

        /** Behavior parameterization **/

    }
}

