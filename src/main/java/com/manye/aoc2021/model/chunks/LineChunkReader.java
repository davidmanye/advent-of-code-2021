package com.manye.aoc2021.model.chunks;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class LineChunkReader {

    public static long calculateIncompletePoints(Deque<Chunk> incompleteChunks) {
        long points = 0;
        while (!incompleteChunks.isEmpty()) {
            points = points * 5 + incompleteChunks.pop().getIncompletePoints();
        }
        return points;
    }

    public static String check(String line) throws CorruptedLineException, IncompleteLineException {
        final Deque<Chunk> stack = new ArrayDeque<>();
        for (String chunk : line.split("")) {
            final var c = Chunk.from(chunk);
            if (c.isOpen(chunk)) {
                stack.push(c);
            } else {
                final var head = stack.pop();
                if (!head.equals(c)) {
                    throw new CorruptedLineException(line, c, head);
                }
            }
        }
        if (!stack.isEmpty()) {
            throw new IncompleteLineException(line, stack);
        }
        return line;
    }

    private static HashMap<Chunk, Deque<String>> initMap() {
        final var stacks = new HashMap<Chunk, Deque<String>>();
        for (Chunk value : Chunk.values()) {
            stacks.put(value, new ArrayDeque<>());
        }
        return stacks;
    }
}
