package com.manye.aoc2021.model.core;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class MatrixDijkstraStateTest {

    @Test
    void getTotalCosts() {
        final var values = InputReader.readAsIntegerMatrix("/Day15/example.txt");
        final var matrix = new Matrix<>(values);
        final var dijkstraState = new MatrixDijkstraState<>(matrix);
        final var start = Coordinate.of(0, 0);
        final var end = Coordinate.of(matrix.getWidth() - 1, matrix.getHeight() - 1);

        dijkstraState.calculateCosts(start,
            entry -> entry.getValue().longValue(),
            entry -> matrix.getAdjacent(entry.getCoordinate()));

        assertThat(dijkstraState.getTotalCost(end)).isEqualTo(40);
    }
}