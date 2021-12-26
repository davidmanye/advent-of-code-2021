package com.manye.aoc2021.model.chunks;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class LineChunkReaderTest {

    @ParameterizedTest
    @MethodSource("provideCorrupted")
    void corruptedLines(String line, String corruptedChunk) {
        Assertions.assertThatThrownBy(() -> LineChunkReader.check(line))
            .isInstanceOf(CorruptedLineException.class)
            .returns(corruptedChunk, ex -> ((CorruptedLineException) ex).getCorruptedChunk().getClose());
    }

    @ParameterizedTest
    @MethodSource("provideUncompleted")
    void uncompletedLines(String line, String remainingChunks) {
        Assertions.assertThatThrownBy(() -> LineChunkReader.check(line))
            .isInstanceOf(IncompleteLineException.class)
            .returns(remainingChunks, ex -> ((IncompleteLineException) ex).getRemainingPart());
    }

    @ParameterizedTest
    @MethodSource("provideIncompletePoints")
    void incompletePoints(String line, Long points) {
        final var chunks = Arrays.stream(line.split("")).map(Chunk::from).collect(Collectors.toCollection(ArrayDeque::new));

        final var result = LineChunkReader.calculateIncompletePoints(chunks);

        assertThat(result).isEqualTo(points);
    }

    public static Stream<Arguments> provideIncompletePoints() {
        return Stream.of(
            Arguments.of("}}]])})]", 288957L),
            Arguments.of(")}>]})", 5566L),
            Arguments.of("}}>}>))))", 1480781L),
            Arguments.of("]]}}]}]}>", 995444L),
            Arguments.of("])}>", 294L)
        );
    }

    public static Stream<Arguments> provideCorrupted() {
        return Stream.of(
            Arguments.of("(]", "]"),
            Arguments.of("{()()()>", ">"),
            Arguments.of("(((()))}", "}"),
            Arguments.of("<([]){()}[{}])", ")"),
            Arguments.of("{([(<{}[<>[]}>{[]{[(<()>", "}"),
            Arguments.of("[[<[([]))<([[{}[[()]]]", ")"),
            Arguments.of("[{[{({}]{}}([{[{{{}}([]", "]"),
            Arguments.of("[<(<(<(<{}))><([]([]()", ")"),
            Arguments.of("<{([([[(<>()){}]>(<<{{", ">")
        );
    }

    public static Stream<Arguments> provideUncompleted() {
        return Stream.of(
            Arguments.of("[({(<(())[]>[[{[]{<()<>>", "}}]])})]"),
            Arguments.of("[(()[<>])]({[<{<<[]>>(", ")}>]})"),
            Arguments.of("(((({<>}<{<{<>}{[]{[]{}", "}}>}>))))"),
            Arguments.of("{<[[]]>}<{[{[{[]{()[[[]", "]]}}]}]}>"),
            Arguments.of("<{([{{}}[<[[[<>{}]]]>[]]", "])}>")
        );
    }
}