package com.manye.aoc2021.model.chunks;

public enum Chunk {
    PARENTHESIS("(", ")", 3, 1),
    SQUARE_BRACKET("[", "]", 57, 2),
    CURLY_BRACKET("{", "}", 1197, 3),
    ARROW_BRACKET("<", ">", 25137, 4)
    ;

    private final String open;
    private final String close;
    private final int corruptedPoints;
    private final int incompletePoints;

    Chunk(String open, String close, int corruptedPoints, int incompletePoints) {
        this.open = open;
        this.close = close;
        this.corruptedPoints = corruptedPoints;
        this.incompletePoints = incompletePoints;
    }

    public String getOpen() {
        return open;
    }

    public String getClose() {
        return close;
    }

    public boolean isOpen(String chunk) {
        return open.equals(chunk.trim());
    }

    public boolean isClose(String chunk) {
        return close.equals(chunk.trim());
    }

    public int getCorruptedPoints() {
        return corruptedPoints;
    }

    public int getIncompletePoints() {
        return incompletePoints;
    }

    public static Chunk from(String chunk) {
        for (Chunk c: Chunk.values()) {
            if (c.isOpen(chunk) || c.isClose(chunk)) {
                return c;
            }
        }
        throw new RuntimeException("Invalid chunk: " + chunk);
    }
}
