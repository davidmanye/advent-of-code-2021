package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class BinaryRateTest {

    @Test
    void getAsBinary() {
        var gammaRate = new BinaryRate(List.of(1, 0, 1, 1, 0), BinaryRate.Type.GAMMA);

        assertThat(gammaRate.getAsBinary()).isEqualTo("10110");
    }

    @Test
    void getAsInt() {
        var gammaRate = new BinaryRate(List.of(1, 0, 1, 1, 0), BinaryRate.Type.EPSILON);

        assertThat(gammaRate.getAsInt()).isEqualTo(22);
    }
}