package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class Day08Test {


    @Test
    void part1() {
        final var displayNotes = InputReader.readDisplayNotes("/Day08/example.txt");

        final var result = Day08.part1(displayNotes);

        assertThat(result).isEqualTo(26);
    }

    @Test
    void part2() {
        final var displayNotes = InputReader.readDisplayNotes("/Day08/example.txt");

        final var result = Day08.part2(displayNotes);

        assertThat(result).isEqualTo(61229);
    }


}