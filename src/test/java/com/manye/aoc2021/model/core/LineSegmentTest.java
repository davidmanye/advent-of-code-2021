package com.manye.aoc2021.model.core;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class LineSegmentTest {

    @ParameterizedTest
    @MethodSource("provideCoordinates")
    void betweenCoordinates(LineSegment segment, List<Coordinate> expectedCoordinates) {

        final List<Coordinate> coordinates = segment.getAllCoordinates();

        assertThat(coordinates).containsExactlyElementsOf(expectedCoordinates);
    }

    private static Stream<Arguments> provideCoordinates() {
        return Stream.of(
            Arguments.of(
                new LineSegment(Coordinate.of(1, 1), Coordinate.of(1, 3)),
                List.of(
                    Coordinate.of(1, 1),
                    Coordinate.of(1, 2),
                    Coordinate.of(1, 3))),
            Arguments.of(
                new LineSegment(Coordinate.of(7, 4), Coordinate.of(7, 1)),
                List.of(
                    Coordinate.of(7, 4),
                    Coordinate.of(7, 3),
                    Coordinate.of(7, 2),
                    Coordinate.of(7, 1))),
            Arguments.of(
                new LineSegment(Coordinate.of(9, 7), Coordinate.of(7, 9)),
                List.of(
                    Coordinate.of(9, 7),
                    Coordinate.of(8, 8),
                    Coordinate.of(7, 9)))
        );
    }
}