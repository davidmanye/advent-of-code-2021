package com.manye.aoc2021.model.bingo;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BingoBoard {

    private final int[][] numbers;
    private final boolean[][] marked;

    private boolean win = false;
    private final HashMap<Integer, AtomicInteger> rowCountMarked = new HashMap<>();
    private final HashMap<Integer, AtomicInteger> columnCountMarked = new HashMap<>();

    public BingoBoard(int[][] numbers) {
        this.numbers = numbers;
        this.marked = new boolean[numbers.length][numbers.length];
    }

    public int get(int row, int column) {
        return numbers[row][column];
    }

    public boolean isMarked(int row, int column) {
        return marked[row][column];
    }

    public void mark(int searchNumber) {
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = 0; j < numbers[i].length; ++j) {
                if (get(i, j) == searchNumber) {
                    if (!marked[i][j]) {
                        marked[i][j] = true;
                        if (increment(rowCountMarked, i)  == 5) {
                            win = true;
                        }
                        if (increment(columnCountMarked, j)  == 5) {
                            win = true;
                        }
                    }
                }
            }
        }
    }

    public long sumUnmarked() {
        var sum = 0;
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = 0; j < numbers[i].length; ++j) {
                if (!marked[i][j]) {
                    sum += numbers[i][j];
                }
            }
        }
        return sum;
    }

    private static int increment(HashMap<Integer, AtomicInteger> map, int index) {
        final AtomicInteger count = map.getOrDefault(index, new AtomicInteger(0));
        count.incrementAndGet();
        map.put(index, count);
        return count.get();
    }

    public boolean hasWin() {
        return win;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("BingoBoard{\n");
        for (int i = 0; i < numbers.length; ++i) {
            for (int j = 0; j < numbers[i].length; ++j) {
                builder.append(numbers[i][j]);
                if (marked[i][j]) {
                    builder.append("[X]");
                } else {
                    builder.append("[ ]");
                }
                builder.append(" ");
            }
            builder.append("\n");
        }
        builder.append("}");
        return builder.toString();
    }
}
