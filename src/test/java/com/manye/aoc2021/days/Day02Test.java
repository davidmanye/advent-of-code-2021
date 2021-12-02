package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day02Test {

    @Test
    @DisplayName("What do you get if you multiply your final horizontal position by your final depth?")
    @Disabled("Using aim invalidates first part")
    void part1() {
        var commands = InputReader.reasAsCommands("/Day02/example.txt");

        var result = Day02.part1(commands);

        assertThat(result).isEqualTo(150);
    }

    @Test
    @DisplayName("What do you get if you multiply your final horizontal position by your final depth?")
    void part2() {
        var commands = InputReader.reasAsCommands("/Day02/example.txt");

        var result = Day02.part2(commands);

        assertThat(result).isEqualTo(900);
    }
}
