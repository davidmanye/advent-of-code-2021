package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class HeightMapTest {

    @Test
    void getLowPoints() {
        final var matrix = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var heightMap = new HeightMap(matrix);

        final var lowPoints = heightMap.getLowPoints();

        assertThat(lowPoints).containsExactlyInAnyOrder(0, 1, 5, 5);
    }
}