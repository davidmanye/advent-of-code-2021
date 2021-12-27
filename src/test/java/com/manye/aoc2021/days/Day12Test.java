package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;

class Day12Test {

    @Test
    void example1() {
        final var graph = InputReader.readAsGraphString("/Day12/example_1.txt");

        final var result = Day12.part1(graph);

        assertThat(result).isEqualTo(10);
    }

    @Test
    void example2() {
        final var graph = InputReader.readAsGraphString("/Day12/example_2.txt");

        final var result = Day12.part1(graph);

        assertThat(result).isEqualTo(19);
    }

    @Test
    void example3() {
        final var graph = InputReader.readAsGraphString("/Day12/example_3.txt");

        final var result = Day12.part1(graph);

        assertThat(result).isEqualTo(226);
    }

    @Test
    void example1_part2() {
        final var graph = InputReader.readAsGraphString("/Day12/example_1.txt");

        final var result = Day12.part2(graph);

        assertThat(result).isEqualTo(36);
    }

    @Test
    void example2_part2() {
        final var graph = InputReader.readAsGraphString("/Day12/example_2.txt");

        final var result = Day12.part2(graph);

        assertThat(result).isEqualTo(103);
    }

    @Test
    void example3_part2() {
        final var graph = InputReader.readAsGraphString("/Day12/example_3.txt");

        final var result = Day12.part2(graph);

        assertThat(result).isEqualTo(3509);
    }

    @Test
    void example1_solution() {
        final var graph = InputReader.readLines("/Day12/example_1.txt").collect(Collectors.toList());

        final var caveNavigator = new Day12.CaveNavigator(graph, true);

        assertThat(caveNavigator.calculatePaths().size()).isEqualTo(36);
    }
}