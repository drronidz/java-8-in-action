package com.drronidz.chapter_5.g_building_streams;

/*
PROJECT NAME : java-8-in-action
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 4/14/2023 5:05 PM
*/

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreams {
    public static void main(String[] args) {
        /* Building Streams */
        /** Stream from values **/
        Stream<String> stream = Stream.of("Java 8", "Lambdas", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // Empty stream
        Stream<String> emptyStream = Stream.empty();

        /** Streams from arrays **/
        // The sum is 41
        int [] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println(sum);

        /** Streams from files **/
        // Streams are autoclosable (automatically closed)
        // Generate a stream of words
        // Remove duplicates
        // Count the number of unique words
        // Deal with the exception if one occurs when opening the file
        long uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /** Streams from functions: creating infinite streams! **/
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // Fibonacci tuple series
        Stream.iterate(new int[]{0, 1}, t -> new int[] { t[1], t[0] + t[1] })
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));

        // Generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        IntSupplier fib = new IntSupplier() {
            private int previous = 0;
            private int current = 1;
            @Override
            public int getAsInt() {
                int oldPrevious = this.previous;
                int nextValue = this.previous + this.current;
                this.previous = this.current;
                this.current = nextValue;
                return oldPrevious;
            }
        };
        IntStream.generate(fib).limit(10).forEach(System.out::println);

    }
}
