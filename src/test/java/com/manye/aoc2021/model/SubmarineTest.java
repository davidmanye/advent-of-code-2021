package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class SubmarineTest {

    private final Submarine submarine = new Submarine();

    @Test
    void runGetsHorizontal() {
        var commands = InputReader.reasAsCommands("/Day02/example.txt");

        submarine.execute(commands);

        assertThat(submarine.getHorizontal()).isEqualTo(15);
    }

    @Test
    void runGetsDepth() {
        var commands = InputReader.reasAsCommands("/Day02/example.txt");

        submarine.execute(commands);

        assertThat(submarine.getDepth()).isEqualTo(60);
    }
}