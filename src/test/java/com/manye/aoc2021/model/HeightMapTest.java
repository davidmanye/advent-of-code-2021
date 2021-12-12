package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.core.Coordinate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class HeightMapTest {

    @Test
    void getLowPoints() {
        final var matrix = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var heightMap = new HeightMap(matrix);

        final var lowPoints = heightMap.getLowPointsValues();

        assertThat(lowPoints).containsExactlyInAnyOrder(0, 1, 5, 5);
    }

    @ParameterizedTest
    @MethodSource("providePoints")
    public void getBasis(Coordinate coordinate, int size) {
        final var matrix = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var heightMap = new HeightMap(matrix);

        final var basis = heightMap.getBasis(coordinate);

        assertThat(basis.size()).isEqualTo(size);
    }

    private static Stream<Arguments> providePoints() {
        return Stream.of(
            Arguments.of(Coordinate.of(1, 0), 3),
            Arguments.of(Coordinate.of(9, 0), 9),
            Arguments.of(Coordinate.of(2, 2), 14),
            Arguments.of(Coordinate.of(7, 4), 9)
        );
    }
}