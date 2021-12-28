package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.Matrix;

import java.util.stream.IntStream;

public class Day15 {

    public static void main(String[] args) {
        final var matrix = InputReader.readMatrixOfIntegers("/Day15/input.txt");

        System.out.println("Part 1: " + part1(matrix));
        System.out.println("Part 1: " + part2(matrix));
    }

    public static long part1(Matrix<Integer> matrix) {
        final var costs = matrix.calculateCostsFrom(Coordinate.of(0, 0), entry -> entry.getValue().longValue());
        return costs.getTotalCost(Coordinate.of(matrix.getWidth() - 1, matrix.getHeight() - 1));
    }

    public static long part2(Matrix<Integer> matrix) {
        return part1(realCave(matrix));
    }

    public static Matrix<Integer> realCave(Matrix<Integer> original) {
        final var multiplyFactor = 5;
        final var newMatrix = new Matrix<>(new Integer[original.getHeight() * multiplyFactor][original.getWidth() * multiplyFactor]);
        original.stream().forEach(entry -> {
            IntStream.range(0, multiplyFactor).forEach(y -> {
                IntStream.range(0, multiplyFactor).forEach(x -> {
                    final var origCoord = entry.getCoordinate();
                    final var newValue = entry.getValue() + x + y;
                    final var roundValue = (newValue > 9) ? newValue - 9 : newValue;
                    newMatrix.set(Coordinate.of((x * original.getWidth()) + origCoord.getX(), (y * original.getHeight()) + origCoord.getY()), currentValue -> roundValue);
                });
            });
        });
        return newMatrix;
    }
}
