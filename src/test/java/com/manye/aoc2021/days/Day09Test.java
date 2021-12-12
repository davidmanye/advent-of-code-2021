package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.HeightMap;

import org.junit.jupiter.api.Test;

class Day09Test {

    @Test
    void part2() {
        final var integers = InputReader.readAsIntegerMatrix("/Day09/example.txt");
        final var heightMap = new HeightMap(integers);

        final var result = Day09.part2(heightMap);

        assertThat(result).isEqualTo(1134);
    }
}