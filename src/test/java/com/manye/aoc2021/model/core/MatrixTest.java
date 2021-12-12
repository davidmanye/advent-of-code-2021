package com.manye.aoc2021.model.core;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class MatrixTest {

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    void adjacent(Coordinate coordinate, List<Integer> values) {
        final var vals = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var matrix = new Matrix<>(vals);

        final var result = matrix.getAdjacentValues(coordinate.getX(), coordinate.getY());

        assertThat(result).containsExactlyInAnyOrderElementsOf(values);
    }

    @ParameterizedTest
    @MethodSource("providePoints")
    void getValue(Coordinate coordinate, Integer value) {
        final var vals = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var matrix = new Matrix<>(vals);

        final var result = matrix.getValue(coordinate.getX(), coordinate.getY());

        assertThat(result).isEqualTo(value);
    }

    private static Stream<Arguments> provideCoordinates() {
        return Stream.of(
            Arguments.of(Coordinate.of(1, 0), asList(2, 9, 9)),
            Arguments.of(Coordinate.of(4, 2), asList(8, 6, 8, 8)),
            Arguments.of(Coordinate.of(9, 4), asList(9, 7))
        );
    }

    private static Stream<Arguments> providePoints() {
        return Stream.of(
            Arguments.of(Coordinate.of(0, 1), 3),
            Arguments.of(Coordinate.of(1, 0), 1),
            Arguments.of(Coordinate.of(9, 0), 0),
            Arguments.of(Coordinate.of(4, 2), 7)
        );
    }
}