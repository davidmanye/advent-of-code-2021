package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Day01Test {

    @Test
    @DisplayName("Example should count the number of times a depth measurement increases from previous")
    void example() {
        var measurement = InputReader.readAsInt("/Day01/example.txt");

        final int result = Day01.result1(measurement);

        assertThat(result).isEqualTo(7);
    }

    @Test
    @DisplayName("Example the number of times the sum of measurements in this sliding window increases")
    void examplePart2() {
        var measurement = InputReader.readAsInt("/Day01/example.txt");

        final int result = Day01.result2(measurement);

        assertThat(result).isEqualTo(5);
    }
}
