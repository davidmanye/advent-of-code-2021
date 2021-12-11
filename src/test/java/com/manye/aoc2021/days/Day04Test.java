package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

public class Day04Test {

    @Test
    void part1() {
        var bingo = InputReader.readBingo("/Day04/example.txt");

        var result = Day04.part1(bingo);

        assertThat(result).isEqualTo(4512);
    }

    @Test
    void part2() {
        var bingo = InputReader.readBingo("/Day04/example.txt");

        var result = Day04.part2(bingo);

        assertThat(result).isEqualTo(1924);
    }
}
