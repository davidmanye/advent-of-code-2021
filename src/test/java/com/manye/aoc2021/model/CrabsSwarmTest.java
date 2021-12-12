package com.manye.aoc2021.model;

import static com.manye.aoc2021.model.CrabsSwarm.FuelCost.INCREMENTAL;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class CrabsSwarmTest {

    @ParameterizedTest
    @MethodSource("providePositions")
    void calculateFuelAtPosition(int position, int expectedFuel) {
        final var crabsSwarm = new CrabsSwarm(new int[] {16, 1, 2, 0, 4, 2, 7, 1, 2, 14});

        final var fuel = crabsSwarm.calculateFuelAtPosition(position);

        assertThat(fuel).isEqualTo(expectedFuel);
    }

    @Test
    void calculateBestFuel() {
        final var crabsSwarm = new CrabsSwarm(new int[] {16, 1, 2, 0, 4, 2, 7, 1, 2, 14});

        final var fuel = crabsSwarm.calculateBestFuel();

        assertThat(fuel).isEqualTo(37);
    }

    @ParameterizedTest
    @MethodSource("provideMovements")
    void move(int start, int end, int expectedFuel) {
        final var crabsSwarm = new CrabsSwarm(new int[] {16, 1, 2, 0, 4, 2, 7, 1, 2, 14}, INCREMENTAL);

        final var fuel = crabsSwarm.move(start, end);

        assertThat(fuel).isEqualTo(expectedFuel);
    }

    private static Stream<Arguments> providePositions() {
        return Stream.of(
            Arguments.of(2, 37),
            Arguments.of(1, 41),
            Arguments.of(3, 39),
            Arguments.of(10, 71)
        );
    }

    private static Stream<Arguments> provideMovements() {
        return Stream.of(
            Arguments.of(16, 5, 66),
            Arguments.of(1, 5, 10),
            Arguments.of(2, 5, 6),
            Arguments.of(2, 2, 0)
        );
    }
}