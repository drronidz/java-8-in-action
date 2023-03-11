package com.drronidz.chapter_3;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/11/2023 3:57 PM
*/

import java.util.Comparator;

public class FilteringTomato {
    public static void main(String[] args) {
        /** Lambda in a nutshell **/

        // Before Lambda expression ...
        Comparator<Tomato> byWeightWithoutLambda = new Comparator<Tomato>() {
            @Override
            public int compare(Tomato tomatoOne, Tomato tomatoTwo) {
                return tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());
            }
        };

        // After Lambda expression ...
        Comparator<Tomato> byWeightWithLambda = (tomatoOne, tomatoTwo) -> tomatoOne.getWeight().compareTo(tomatoTwo.getWeight());

        // After Optimizing
        Comparator<Tomato> byWeightOpt = Comparator.comparing(Tomato::getWeight);
    }
}
