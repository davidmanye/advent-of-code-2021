package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.HeightMap;

public class Day09 {

    public static void main(String[] args) {
        final var integers = InputReader.readAsIntegerMatrix("/Day09/input.txt");
        final var heightMap = new HeightMap(integers);

        System.out.println("Part 1: " + part1(heightMap));
    }

    public static long part1(HeightMap heightMap) {
        return heightMap
            .getRiskLevelOfLowPoints()
            .stream()
            .mapToInt(Integer::intValue)
            .sum();
    }
}
