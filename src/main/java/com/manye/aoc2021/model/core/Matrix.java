package com.manye.aoc2021.model.core;

import lombok.Value;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Matrix<T> {

    @Value
    public class Entry {
        Coordinate coordinate;
        T value;
    }

    private final T[][] matrix;

    public Matrix(T[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * Not include diagonal.
     */
    public List<T> getAdjacentValues(Coordinate coordinate) {
        return getAdjacentValues(coordinate.getX(), coordinate.getY());
    }

    /**
     * Not include diagonal.
     */
    public List<T> getAdjacentValues(int x, int y) {
        return Stream.of(
            getValueIfExists(x + 1, y),
            getValueIfExists(x - 1, y),
            getValueIfExists(x, y + 1),
            getValueIfExists(x, y - 1)
        )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public T getValue(Coordinate coordinate) {
        return getValue(coordinate.getX(), coordinate.getY());
    }

    public T getValue(int x, int y) {
        return matrix[y][x];
    }

    public Optional<T> getValueIfExists(int x, int y) {
        if (0 <= y && y < matrix.length) {
            if (0 <= x && x < matrix[y].length) {
                return Optional.of(matrix[y][x]);
            }
        }
        return Optional.empty();
    }

    public Stream<Entry> stream() {
        return IntStream
            .range(0, matrix.length)
            .boxed()
            .flatMap(y -> IntStream.range(0, matrix[y].length).mapToObj(x -> {
                final var coord = Coordinate.of(x, y);
                return new Entry(coord, getValue(coord));
            }));
    }

}
