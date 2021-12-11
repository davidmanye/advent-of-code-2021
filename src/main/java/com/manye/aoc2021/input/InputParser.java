package com.manye.aoc2021.input;

import static java.util.function.Predicate.not;

import com.manye.aoc2021.model.Command;
import com.manye.aoc2021.model.bingo.BingoBoard;

import java.util.Arrays;

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


}
