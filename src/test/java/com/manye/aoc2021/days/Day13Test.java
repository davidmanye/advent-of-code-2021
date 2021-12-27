package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day13Test {

    @Test
    void part1() {
        final var all = InputReader.readAll("/Day13/example.txt");
        final var split = all.split("\n\n");
        final var transparentPaper = Day13.readMatrix(split[0]);
        final var instructions = Day13.readInstructions(split[1]);

        final var result = Day13.part1(transparentPaper, instructions);

        assertThat(result).isEqualTo(17);
    }
}