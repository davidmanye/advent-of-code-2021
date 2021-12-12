package com.manye.aoc2021.model.display;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputParser;

import org.junit.jupiter.api.Test;

class DisplayNoteResolverTest {

    @Test
    void resolve() {
        final var displayNote = InputParser.parseDisplayNote("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");

        var result = DisplayNoteResolver.resolve(displayNote);

        assertThat(result).isEqualTo(5353);
    }
}