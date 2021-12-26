package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day11Test {

    @Test
    void part1() {
        final var matrix1 = InputReader.readAsIntegerMatrix("/Day11/example.txt");

        final var result = Day11.part1(matrix1);

        assertThat(result).isEqualTo(1656);
    }
}