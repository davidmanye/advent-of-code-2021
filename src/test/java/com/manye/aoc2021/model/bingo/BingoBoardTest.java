package com.manye.aoc2021.model.bingo;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputParser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class BingoBoardTest {

    @ParameterizedTest
    @MethodSource("provideMarked")
    void mark(int searchNumber, int row, int column) {
        final String board =
            "22 13 17 11  0\n"
            + " 8  2 23  4 24\n"
            + "21  9 14 16  7\n"
            + " 6 10  3 18  5\n"
            + " 1 12 20 15 19";
        var bingoBoard = InputParser.parseBingoBoard(board);

        bingoBoard.mark(searchNumber);

        assertThat(bingoBoard.get(row, column)).isEqualTo(searchNumber);
        assertThat(bingoBoard.isMarked(row, column)).isEqualTo(true);
    }

    @ParameterizedTest
    @MethodSource("provideWin")
    void win(int[] numbers, boolean isWin) {
        final String board =
            "22 13 17 11  0\n"
                + " 8  2 23  4 24\n"
                + "21  9 14 16  7\n"
                + " 6 10  3 18  5\n"
                + " 1 12 20 15 19";
        var bingoBoard = InputParser.parseBingoBoard(board);

        for (int number: numbers) {
            bingoBoard.mark(number);
        }

        assertThat(bingoBoard.hasWin()).isEqualTo(isWin);
    }

    @Test
    void sumUnmarked() {
        final var board = "14 21 17 24  4\n"
            + "10 16 15  9 19\n"
            + "18  8 23 26 20\n"
            + "22 11 13  6  5\n"
            + " 2  0 12  3  7";
        var bingoBoard = InputParser.parseBingoBoard(board);

        for (int num: new int[]{7,4,9,5,11,17,23,2,0,14,21,24}) {
            bingoBoard.mark(num);
        }

        assertThat(bingoBoard.hasWin()).isTrue();
        assertThat(bingoBoard.sumUnmarked()).isEqualTo(188);
    }

    private static Stream<Arguments> provideMarked() {
        return Stream.of(
            Arguments.of(22, 0, 0),
            Arguments.of(2, 1, 1),
            Arguments.of(1, 4, 0),
            Arguments.of(5, 3, 4)
        );
    }

    private static Stream<Arguments> provideWin() {
        return Stream.of(
            Arguments.of(new int[]{22,13,17,11,0}, true),
            Arguments.of(new int[]{11,4,16,18,15}, true),
            Arguments.of(new int[]{6,2,23,4,0}, false)
        );
    }
}