package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.utils.StreamUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Day01 {

    public static void main(String[] args) {
        var measurements = InputReader.readAsInt("/Day01/input.txt");
        System.out.println("Part 1: " + result1(measurements));
        System.out.println("Part 1: " + result2(measurements));
    }

    public static int result1(List<Integer> measurements) {
        return numberOfIncreases(measurements);
    }

    public static int result2(List<Integer> measurements) {
        var sliding = StreamUtils
            .sliding(measurements, 3)
            .map(list -> list.stream().mapToInt(Integer::intValue).sum())
            .collect(Collectors.toList());
        return numberOfIncreases(sliding);
    }

    private static int numberOfIncreases(List<Integer> measurements) {
        return StreamUtils
            .sliding(measurements, 2)
            .mapToInt(pair -> pair.get(0) < pair.get(1) ? 1 : 0)
            .sum();
    }
}
