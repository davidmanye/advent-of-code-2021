package com.manye.aoc2021.model.chunks;

import java.util.Deque;
import java.util.stream.Collectors;

public class IncompleteLineException extends RuntimeException {

    private final String line;
    private final Deque<Chunk> incompleteChunks;

    public IncompleteLineException(String line, Deque<Chunk> incompleteChunks) {
        this.line = line;
        this.incompleteChunks = incompleteChunks;
    }

    public String getLine() {
        return line;
    }

    public Deque<Chunk> getIncompleteChunks() {
        return incompleteChunks;
    }

    public String getRemainingPart() {
        return incompleteChunks.stream().map(Chunk::getClose).collect(Collectors.joining(""));
    }
}
