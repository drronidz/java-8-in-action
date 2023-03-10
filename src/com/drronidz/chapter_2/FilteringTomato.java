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

    /*  2.1.3 Third attempt: filtering with every attribute ou can think of */
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

    public static interface TomatoPredicate {
        boolean test (Tomato tomato);
    }

    public static class TomatoGreenColorPredicate implements TomatoPredicate {
        @Override
        public boolean test(Tomato tomato) {
            return "green".equals(tomato.getColor());
        }
    }

    public static class TomatoHeavyWeightPredicate implements TomatoPredicate {
        @Override
        public boolean test(Tomato tomato) {
            return tomato.getWeight() > 150;
        }
    }

    public static class TomatoRedAndHeavyPredicate implements TomatoPredicate {
        @Override
        public boolean test(Tomato tomato) {
            return "red".equals(tomato.getColor()) && tomato.getWeight() > 150;
        }
    }

    /*  2.2.1 Fourth attempt: filtering by abstract criteria */
    public static List<Tomato> filterTomatoes(List<Tomato> inventory, TomatoPredicate predicate) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if (predicate.test(tomato)) {
                result.add(tomato);
            }
        }
        return result;
    }

    /* Quiz : write a flexible prettyPrintTomato method */
    public static interface TomatoFormatter {
        String accept(Tomato tomato);
    }

    public static class TomatoFancyFormatter implements TomatoFormatter {
        @Override
        public String accept(Tomato tomato) {
            String characteristic = tomato.getWeight() > 150 ? "heavy" : "light";
            return "A " + characteristic + " " + tomato.getColor() + " tomato";
        }
    }

    public static class TomatoSimpleFormatter implements TomatoFormatter {
        @Override
        public String accept(Tomato tomato) {
            return "A tomato of " + tomato.getWeight() + "g";
        }
    }

    public static void prettyPrintTomato(List<Tomato> inventory,TomatoFormatter formatter) {
        for (Tomato tomato: inventory) {
            String output = formatter.accept(tomato);
            System.out.println(output);
        }
    }

    /** Tackling Verbosity **/
    /* Anonymous classes */
    /*  2.3.2 Fifth attempt: using an anonymous class */



    /** Preview of lambda expressions **/
    /** Real-world examples: Comparator, Runnable, and GUI **/

    public static void main(String[] args) {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(190, "red"),
                new Tomato(150, "yellow"),
                new Tomato(180, "green"),
                new Tomato(110, "purple"));

        /** 2.1 Coping with changing requirements **/
        List<Tomato> greenTomatoes = filterTomatoesByColor(inventory, "green");
        List<Tomato> heavyTomatoes = filterTomatoesByWeight(inventory, 150);

        /** Behavior parameterization **/
        List<Tomato> heavyTomatoesWithPredicate = filterTomatoes(inventory, new TomatoHeavyWeightPredicate());
        List<Tomato> redAndHeavyTomatoes = filterTomatoes(inventory, new TomatoRedAndHeavyPredicate());

        // with Fancy formatter ...
        prettyPrintTomato(inventory, new TomatoFancyFormatter());
        // with Simple formatter ...
        prettyPrintTomato(inventory, new TomatoSimpleFormatter());


        /** Tackling Verbosity **/
        /* Anonymous classes */
        /*  2.3.2 Fifth attempt: using an anonymous class */
        List<Tomato> redTomatoes = filterTomatoes(inventory, new TomatoPredicate() {
            @Override
            public boolean test(Tomato tomato) {
                return "red".equals(tomato.getColor());
            }
        });
    }
}

