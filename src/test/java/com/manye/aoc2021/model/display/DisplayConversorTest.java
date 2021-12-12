package com.manye.aoc2021.model.display;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputParser;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DisplayConversorTest {

    @ParameterizedTest
    @MethodSource("provideSignals")
    void convert(String signal, String result) {
        final var displayNote = InputParser.parseDisplayNote("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf");
        final var config = WireConfiguration.from(displayNote.getSignalPatterns());
        final var conversor = new DisplayConversor(config);

        final var number = conversor.convert(signal);

        assertThat(number).isEqualTo(result);
    }

    private static Stream<Arguments> provideSignals() {
        return Stream.of(
            Arguments.of("acedgfb", "8"),
            Arguments.of("cdfbe", "5"),
            Arguments.of("gcdfa", "2"),
            Arguments.of("fbcad", "3"),
            Arguments.of("dab", "7"),
            Arguments.of("cefabd", "9"),
            Arguments.of("cdfgeb", "6"),
            Arguments.of("eafb", "4"),
            Arguments.of("cagedb", "0"),
            Arguments.of("ab", "1")
        );
    }

}