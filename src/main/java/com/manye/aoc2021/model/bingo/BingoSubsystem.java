package com.manye.aoc2021.model.bingo;

import java.util.ArrayList;
import java.util.List;

public class BingoSubsystem {

    private int currentDrawNumberIndex = 0;
    private final List<Integer> drawNumbers;
    private final List<BingoBoard> boards;
    private final List<BingoBoard> winners = new ArrayList<>();

    public BingoSubsystem(List<Integer> drawNumbers, List<BingoBoard> boards) {
        this.drawNumbers = drawNumbers;
        this.boards = boards;
    }

    public boolean hasNext() {
        return currentDrawNumberIndex < drawNumbers.size();
    }

    public int next() {
        currentDrawNumberIndex += 1;
        return drawNumbers.get(currentDrawNumberIndex);
    }

    public int getCurrentDrawNumber() {
        return drawNumbers.get(currentDrawNumberIndex);
    }

    public int runNext() {
        var drawNumber = getCurrentDrawNumber();
        var win = false;
        for (BingoBoard board: boards) {
            board.mark(drawNumber);
            if (!winners.contains(board) && board.hasWin()) {
                winners.add(board);
            }
        }
        return drawNumber;
    }

    public List<BingoBoard> getWinners() {
        return winners;
    }

    public boolean hasWinners() {
        return winners.size() > 0;
    }

    public int getBoardsSize() {
        return boards.size();
    }


    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder("BingoSubsystem{");
        builder.append(drawNumbers);
        builder.append("\n");
        for (BingoBoard board: boards) {
            builder.append(board);
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
