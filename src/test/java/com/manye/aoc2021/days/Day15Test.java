package com.manye.aoc2021.days;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.core.Matrix;

import org.junit.jupiter.api.Test;

class Day15Test {


    @Test
    void part1() {
        final var matrix = InputReader.readMatrixOfIntegers("/Day15/example.txt");

        final var result = Day15.part1(matrix);

        assertThat(result).isEqualTo(40);
    }

    @Test
    void part2() {
        final var matrix = InputReader.readMatrixOfIntegers("/Day15/example.txt");

        final var result = Day15.part2(matrix);

        assertThat(result).isEqualTo(315);
    }

    @Test
    void realCave() {
        final var matrix = new Matrix<>(new Integer[][]{new Integer[]{8}});

        final var newMatrix = Day15.realCave(matrix);

        assertThat(newMatrix.toString()).isEqualTo(
            "89123\n"
            + "91234\n"
            + "12345\n"
            + "23456\n"
            + "34567\n");
    }
}