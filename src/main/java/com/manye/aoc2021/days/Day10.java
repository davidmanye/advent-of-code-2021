package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.chunks.Chunk;
import com.manye.aoc2021.model.chunks.CorruptedLineException;
import com.manye.aoc2021.model.chunks.IncompleteLineException;
import com.manye.aoc2021.model.chunks.LineChunkReader;

import io.vavr.control.Try;

import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    public static void main(String[] args) {
        final var lines = InputReader.readLines("/Day10/input.txt").collect(Collectors.toList());

        System.out.println("Part 1: " + part1(lines));
        System.out.println("Part 2: " + part2(lines));
    }

    public static long part1(List<String> lines) {
        return lines
            .stream()
            .map(line -> Try.of(() -> LineChunkReader.check(line)))
            .filter(Try::isFailure)
            .map(Try::failed)
            .map(Try::get)
            .filter(throwable -> throwable instanceof CorruptedLineException)
            .map(ex -> ((CorruptedLineException) ex))
            .map(CorruptedLineException::getCorruptedChunk)
            .mapToInt(Chunk::getCorruptedPoints)
            .sum();
    }

    public static long part2(List<String> lines) {
        final var values = lines
            .stream()
            .map(line -> Try.of(() -> LineChunkReader.check(line)))
            .filter(Try::isFailure)
            .map(Try::failed)
            .map(Try::get)
            .filter(throwable -> throwable instanceof IncompleteLineException)
            .map(ex -> ((IncompleteLineException) ex))
            .map(IncompleteLineException::getIncompleteChunks)
            .mapToLong(LineChunkReader::calculateIncompletePoints)
            .sorted()
            .toArray();
        System.out.println("Scores length: " + values.length);
        return values[values.length / 2];
    }

}


