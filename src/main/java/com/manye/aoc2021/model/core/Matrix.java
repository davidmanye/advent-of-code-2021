package com.manye.aoc2021.model.core;

import static java.util.Collections.emptyList;

import lombok.Value;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
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

    public List<T> getAdjacentValues(Coordinate coordinate) {
        return getAdjacentValues(coordinate.getX(), coordinate.getY());
    }

    /**
     * Not include diagonal.
     */
    public List<Entry> getAdjacent(int x, int y) {
        return Stream.of(
                getEntryIfExists(x + 1, y),
                getEntryIfExists(x - 1, y),
                getEntryIfExists(x, y + 1),
                getEntryIfExists(x, y - 1)
            )
            .filter(Optional::isPresent)
            .map(Optional::get)
            .collect(Collectors.toList());
    }

    public List<T> getAdjacentValues(int x, int y) {
        return getAdjacent(x, y)
            .stream()
            .map(Entry::getValue)
            .collect(Collectors.toList());
    }

    public List<Entry> getAdjacent(Coordinate coordinate) {
        return getAdjacent(coordinate.getX(), coordinate.getY());
    }

    public List<Entry> searchValuesFrom(Coordinate coordinate, Predicate<T> match) {
        return searchValuesFrom(new HashSet<>(), coordinate, match);
    }

    public List<Entry> searchValuesFrom(Set<Coordinate> visited, Coordinate coordinate, Predicate<T> match) {
        if (!visited.contains(coordinate)) {
            final var value = getValue(coordinate);
            if (match.test(value)) {
                final List<Entry> values = new ArrayList<>();
                values.add(new Entry(coordinate, value));
                visited.add(coordinate);

                getAdjacent(coordinate)
                    .stream()
                    .map(adjacent -> searchValuesFrom(visited, adjacent.getCoordinate(), match))
                    .forEach(values::addAll);
                return values;
            }
        }
        return emptyList();
    }

    public T getValue(Coordinate coordinate) {
        return getValue(coordinate.getX(), coordinate.getY());
    }

    public T getValue(int x, int y) {
        return matrix[y][x];
    }

    public Optional<T> getValueIfExists(int x, int y) {
        if (isInBound(x, y)) {
            return Optional.of(matrix[y][x]);
        }
        return Optional.empty();
    }

    public boolean isInBound(int x, int y) {
        if (0 <= y && y < matrix.length) {
            return 0 <= x && x < matrix[y].length;
        }
        return false;
    }

    public Optional<Entry> getEntryIfExists(int x, int y) {
        if (isInBound(x, y)) {
            final var coord = Coordinate.of(x, y);
            return Optional.of(new Entry(coord, getValue(coord)));
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
