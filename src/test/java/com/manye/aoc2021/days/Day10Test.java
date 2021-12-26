package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class Day10Test {

    @Test
    void part1() {
        final var lines = InputReader.readLines("/Day10/example.txt").collect(Collectors.toList());

        final var result = Day10.part1(lines);

        assertThat(result).isEqualTo(26397);
    }

    @Test
    void part2() {
        final var lines = InputReader.readLines("/Day10/example.txt").collect(Collectors.toList());

        final var result = Day10.part2(lines);

        assertThat(result).isEqualTo(288957);
    }
}