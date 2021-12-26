package com.manye.aoc2021.model.chunks;

public class CorruptedLineException extends RuntimeException {

    private final String line;
    private final Chunk corruptedChunk;
    private final Chunk expectedChunk;

    public CorruptedLineException(String line, Chunk corruptedChunk, Chunk expectedChunk) {
        this.line = line;
        this.corruptedChunk = corruptedChunk;
        this.expectedChunk = expectedChunk;
    }

    public String getLine() {
        return line;
    }

    public Chunk getCorruptedChunk() {
        return corruptedChunk;
    }

    public Chunk getExpectedChunk() {
        return expectedChunk;
    }
}
