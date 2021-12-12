package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day07Test {

    @Test
    void part1() {
        final var positions = InputReader.readAsIntArray("/Day07/example.txt");

        final var result = Day07.part1(positions);

        assertThat(result).isEqualTo(37);
    }

    @Test
    void part2() {
        final var positions = InputReader.readAsIntArray("/Day07/example.txt");

        final var result = Day07.part2(positions);

        assertThat(result).isEqualTo(168);
    }
}