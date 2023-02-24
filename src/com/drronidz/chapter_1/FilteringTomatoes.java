package com.drronidz.chapter_1;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 2/24/2023 11:44 AM
*/

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class FilteringTomatoes {

    public static void main(String[] args) {

        List<Tomato> inventory = Arrays.asList(
                new Tomato(80, "red"),
                new Tomato(150, "green"),
                new Tomato(110, "purple"));

        // verbose code to sort a list of tomatoes in inventory based on their weight :
        Collections.sort(inventory, new Comparator<Tomato>() {
            @Override
            public int compare(Tomato tomatoOne, Tomato tomatoTwo) {
                return tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());
            }
        });

        // concise code in Java 8 of the previous problem :
        inventory.sort(comparing(Tomato::getWeight));

        /** Functions in Java **/

        /* Methods and lambdas as first-class citizens */

        // Filtering hidden files old way!

        File[] hiddenFilesOldWay = new File(".").listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isHidden();
            }
        });

        // Filtering hidden files new way! using method reference syntax (::)
        File[] hiddenFilesNewWay = new File(".").listFiles(File::isHidden);

        /* Passing code: by example */

        // Calling filterTomatoes by Predicate (isGreenTomato to get all Green Tomatoes in inventory)
        filterTomatoes(inventory, Tomato::isGreenTomato);

        // Calling filterTomatoes by Predicate (isHeavyTomato to get all Heavy Tomatoes in inventory)
        filterTomatoes(inventory, Tomato::isHeavyTomato);

        // From passing methods by predicates to lambdas :
        filterTomatoes(inventory, (Tomato tomato) -> "green".equals(tomato.getColor()));
        filterTomatoes(inventory, (Tomato tomato) -> tomato.getWeight() > 150);
        filterTomatoes(inventory, (Tomato tomato) -> tomato.getWeight() < 80 || "brown".equals(tomato.getColor()));


    }

    public static class Tomato {
        private int weight = 0;
        private String color = "";

        public Tomato(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        // (Filtering all green tomatoes) After Java 8
        public static boolean isGreenTomato(Tomato tomato) {
            return "green".equals(tomato.getColor());
        }

        // (Filtering all heavy Tomatoes) After Java 8
        public static boolean isHeavyTomato(Tomato tomato) {
            return tomato.getWeight() > 150;
        }

        @Override
        public String toString() {
            return "Tomato{" +
                    "weight=" + weight +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    // (Filtering all green Tomatoes) Before Java 8
    public static List<Tomato> filterGreenTomatoes(List<Tomato> inventory) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if ("green".equals(tomato.getColor())) {
                result.add(tomato);
            }
        }
        return result;
    }

    // (Filtering all heavy Tomatoes) Before Java 8
    public static List<Tomato> filterHeavyTomatoes(List<Tomato> inventory) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if (tomato.getWeight() > 150) {
                result.add(tomato);
            }
        }
        return result;
    }



    // (Filtering Tomatoes (global method)) After Java 8
    public static List<Tomato> filterTomatoes (List<Tomato> inventory, Predicate<Tomato> tomatoPredicate) {
        List<Tomato> result = new ArrayList<>();
        for (Tomato tomato: inventory) {
            if (tomatoPredicate.test(tomato)) {
                result.add(tomato);
            }
        }
        return result;
    }
}
