package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day06Test {

    @Test
    void part1() {
        final var integers = InputReader.readAsIntArray("/Day06/example.txt");

        final long fishes = Day06.part1(integers);

        assertThat(fishes).isEqualTo(5934);
    }
}