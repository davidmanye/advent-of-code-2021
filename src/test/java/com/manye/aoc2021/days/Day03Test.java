package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day03Test {

    @Test
    @DisplayName("What is the power consumption of the submarine?")
    void part1() {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/example.txt");

        var result = Day03.part1(diagnosticReport);

        assertThat(result).isEqualTo(198);
    }

    @Test
    @DisplayName("What is the life support rating of the submarine?")
    void part2() {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/example.txt");

        var result = Day03.part2(diagnosticReport);

        assertThat(result).isEqualTo(230);
    }
}
