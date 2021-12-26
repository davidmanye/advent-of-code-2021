package com.manye.aoc2021.model;

import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.Matrix;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class OctopusMatrix {

    private static final Function<Integer, Integer> INCREASE_ENERGY = energy -> energy + 1;
    private final Matrix<Integer> matrix;

    public OctopusMatrix(Integer[][] matrix) {
        this.matrix = new Matrix<>(matrix);
    }

    public long runStep(long numStep) {
        final Set<Coordinate> flashed = new HashSet<>();
        increaseEnergies();
        applyFlashes(flashed);
        resetFlashes();
        return flashed.size();
    }

    private void resetFlashes() {
        matrix
            .stream()
            .filter(entry -> entry.getValue() > 9)
            .forEach(entry -> {
                matrix.set(entry.getCoordinate(), v -> 0);
            });
    }

    private void applyFlashes(Set<Coordinate> flashed) {
        matrix
            .stream()
            .filter(entry -> entry.getValue() > 9)
            .forEach(entry -> flash(flashed, entry.getCoordinate()));
    }

    private void flash(Set<Coordinate> flashed, Coordinate coordinate) {
        if (!flashed.contains(coordinate)) {
            flashed.add(coordinate);
            matrix
                .getAllAdjacent(coordinate)
                .forEach(adjacent -> {
                    final var value = matrix.set(adjacent.getCoordinate(), INCREASE_ENERGY);
                    if (value > 9) {
                        flash(flashed, adjacent.getCoordinate());
                    }
                });
        }
    }

    private void increaseEnergies() {
        matrix
            .streamCoordinates()
            .forEach(coordinate -> matrix.set(coordinate, INCREASE_ENERGY));
    }

    public int simulateUntilAllFlashes() {
        int step = 0;
        while (!matrix.stream().allMatch(i -> i.getValue() == 0)) {
            runStep(step);
            step++;
        }
        return step;
    }
}
