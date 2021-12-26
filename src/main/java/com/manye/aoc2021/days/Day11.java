package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.OctopusMatrix;

import java.util.stream.LongStream;

public class Day11 {

    public static void main(String[] args) {
        final var matrix1 = InputReader.readAsIntegerMatrix("/Day11/input.txt");
        final var matrix2 = InputReader.readAsIntegerMatrix("/Day11/input.txt");

        System.out.println("Part 1: " + part1(matrix1));
        System.out.println("Part 2: " + part2(matrix2));
    }

    public static long part1(Integer[][] init) {
        final OctopusMatrix matrix = new OctopusMatrix(init);
        return LongStream.range(0, 100)
            .map(matrix::runStep)
            .sum();
    }

    public static long part2(Integer[][] init) {
        final OctopusMatrix matrix = new OctopusMatrix(init);
        return matrix.simulateUntilAllFlashes();
    }
}
