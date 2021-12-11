package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.bingo.BingoSubsystem;

public class Day04 {

    public static void main(String[] args) {
        var bingo1 = InputReader.readBingo("/Day04/input.txt");
        var bingo2 = InputReader.readBingo("/Day04/input.txt");

        var result1 = part1(bingo1);
        var result2 = part2(bingo2);

        System.out.println("Part 1: " + result1);
        System.out.println("Part 2: " + result2);
    }

    public static long part1(BingoSubsystem bingoSubsystem) {
        while(bingoSubsystem.hasNext()) {
            var drawNumber = bingoSubsystem.runNext();
            if (bingoSubsystem.hasWinners()) {
                var winners = bingoSubsystem.getWinners();
                if (winners.size() > 1) {
                    throw new RuntimeException("Too much winners");
                }
                var sum = winners.get(0).sumUnmarked();
                return sum * drawNumber;
            }
            bingoSubsystem.next();
        }
        throw new RuntimeException("No winners");
    }

    public static long part2(BingoSubsystem bingoSubsystem) {
        while(bingoSubsystem.hasNext()) {
            var drawNumber = bingoSubsystem.runNext();
            if (bingoSubsystem.getWinners().size() == bingoSubsystem.getBoardsSize()) {
                final var winners = bingoSubsystem.getWinners();
                final var lastWinner = winners.get(winners.size() - 1);
                return lastWinner.sumUnmarked() * drawNumber;
            }
            bingoSubsystem.next();
        }

        throw new RuntimeException("No winners");
    }
}
