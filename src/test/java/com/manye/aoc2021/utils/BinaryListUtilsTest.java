package com.manye.aoc2021.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class BinaryListUtilsTest {

    @Test
    void returnMostCommon() {
        var list = List.of(1, 0, 1, 1, 0);

        var result = BinaryListUtils.mostCommon(list);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void returnLessCommon() {
        var list = List.of(1, 0, 1, 1, 0);

        var result = BinaryListUtils.lessCommon(list);

        assertThat(result).isEqualTo(0);
    }

    @Test
    void returnMinusOneIfLessCommonAreEqual() {
        var list = List.of(1, 1, 0, 0);

        var result = BinaryListUtils.lessCommon(list);

        assertThat(result).isEqualTo(-1);
    }

    @Test
    void returnMinusOneIfMostCommonAreEqual() {
        var list = List.of(1, 1, 0, 0);

        var result = BinaryListUtils.mostCommon(list);

        assertThat(result).isEqualTo(-1);
    }
}