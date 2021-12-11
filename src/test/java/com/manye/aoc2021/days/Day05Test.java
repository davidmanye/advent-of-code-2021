package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day05Test {

    @Test
    void part1() {
        final var lineSegments = InputReader.readLineSegments("/Day05/example.txt");

        final var points = Day05.part1(lineSegments);

        assertThat(points).isEqualTo(5);
    }
}