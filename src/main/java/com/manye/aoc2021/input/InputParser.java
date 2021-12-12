package com.manye.aoc2021.input;

import static java.util.function.Predicate.not;

import com.manye.aoc2021.model.Command;
import com.manye.aoc2021.model.bingo.BingoBoard;
import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.LineSegment;
import com.manye.aoc2021.model.display.DisplayNote;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public final class InputParser {

    public static Command parseCommand(String s) {
        var split = s.split(" ");
        return new Command(Command.Type.valueOf(split[0]), Integer.parseInt(split[1]));
    }

    public static BingoBoard parseBingoBoard(String square) {
        final int[] numbers = Arrays.stream(square.split("\\s+")).filter(not(String::isBlank)).mapToInt(Integer::parseInt).toArray();
        final int[][] board = new int[5][5];
        for (int i = 0; i < 5; ++i) {
            System.arraycopy(numbers, 5 * i, board[i], 0, 5);
        }
        return new BingoBoard(board);
    }

    public static LineSegment parseLineSegment(String line) {
        final var split = line.split("->");
        return new LineSegment(parseCoordinate(split[0]), parseCoordinate(split[1]));
    }

    public static Coordinate parseCoordinate(String coordinates) {
        final var split = coordinates.trim().split(",");
        return new Coordinate(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }

    public static DisplayNote parseDisplayNote(String line) {
        final var split = line.split("\\|");
        return new DisplayNote(parseStringList(split[0]), parseStringList(split[1]));
    }

    private static List<String> parseStringList(String part) {
        return Arrays.stream(part.split(" "))
            .map(String::trim)
            .filter(not(String::isBlank))
            .collect(Collectors.toList());
    }
}
