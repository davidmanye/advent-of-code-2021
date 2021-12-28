package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day14Test {

    @Test
    void part1() {
        final var polymer = InputReader.readPolymer("/Day14/example.txt");

        final var result = Day14.part1(polymer);

        assertThat(result).isEqualTo(1588L);
    }

    @Test
    void part2() {
        final var polymer = InputReader.readPolymer("/Day14/example.txt");

        final var result = Day14.part2(polymer);

        assertThat(result).isEqualTo(2188189693529L);
    }
}