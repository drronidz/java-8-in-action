package com.drronidz.chapter_3;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 3/10/2023 3:47 PM
*/


public class Tomato {
    private int weight = 0;
    private String color = "";

    public Tomato(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Tomato() {

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


    @Override
    public String toString() {
        return "Tomato{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}
