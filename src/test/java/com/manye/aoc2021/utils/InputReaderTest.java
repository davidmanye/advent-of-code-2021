package com.manye.aoc2021.utils;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

public class InputReaderTest {

    @Test
    void readBingo() {
        final var bingo = InputReader.readBingo("/Day04/example.txt");

        System.out.println(bingo);
    }
}
