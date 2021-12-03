package com.manye.aoc2021.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import java.util.List;

class ListUtilsTest {

    @Test
    void returnMostCommon() {
        var list = List.of(1, 0, 1, 1, 0);

        var result = ListUtils.mostCommon(list);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void returnLessCommon() {
        var list = List.of(1, 0, 1, 1, 0);

        var result = ListUtils.lessCommon(list);

        assertThat(result).isEqualTo(0);
    }
}