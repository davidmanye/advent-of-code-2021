package com.manye.aoc2021.model.core;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;
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

    @Test
    void transposeVertically() {
        final var vals = InputReader.readAsStringMatrix("/example/letter_matrix.txt");
        final Matrix<String> matrix = new Matrix<>(vals);

        final var newMatrix = matrix.transposeVertical();

        assertThat(newMatrix.toString()).isEqualTo(
            "PKRST\n"
                + "KLMNO\n"
                + "FGHIJ\n"
                + "ABCDE\n");
    }

    @Test
    void transposeHorizontally() {
        final var vals = InputReader.readAsStringMatrix("/example/letter_matrix.txt");
        final Matrix<String> matrix = new Matrix<>(vals);

        final var newMatrix = matrix.transposeHorizontally();

        assertThat(newMatrix.toString()).isEqualTo(
            "EDCBA\n"
                + "JIHGF\n"
                + "ONMLK\n"
                + "TSRKP\n");
    }

    @Test
    void merge() {
        final var vals = InputReader.readAsIntegerMatrix("/example/number_matrix.txt");
        final var vals2 = InputReader.readAsIntegerMatrix("/example/number_matrix.txt");
        final Matrix<Integer> matrix = new Matrix<>(vals);
        final Matrix<Integer> matrix2 = new Matrix<>(vals2);

        final var newMatrix = matrix.merge(matrix2, Integer::sum);

        assertThat(newMatrix.toString()).isEqualTo(
            "24680\n"
                + "86420\n"
                + "46802\n"
                + "04682\n");
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