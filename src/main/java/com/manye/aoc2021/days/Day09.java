package com.manye.aoc2021.days;

import static java.util.Comparator.reverseOrder;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.HeightMap;

import java.util.List;

public class Day09 {

    public static void main(String[] args) {
        final var integers = InputReader.readAsIntegerMatrix("/Day09/input.txt");
        final var heightMap = new HeightMap(integers);

        System.out.println("Part 1: " + part1(heightMap));
        System.out.println("Part 2: " + part2(heightMap));
    }

    public static long part1(HeightMap heightMap) {
        return heightMap
            .getRiskLevelOfLowPoints()
            .stream()
            .mapToInt(Integer::intValue)
            .sum();
    }

    public static long part2(HeightMap heightMap) {
        return heightMap
            .getLowPoints()
            .map(lowPointEntry -> heightMap.getBasis(lowPointEntry.getCoordinate()))
            .map(List::size)
            .sorted(reverseOrder())
            .limit(3)
            .mapToInt(Integer::intValue)
            .reduce(1, (x, y) -> x * y);
    }
}
