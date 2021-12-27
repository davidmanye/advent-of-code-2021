package com.manye.aoc2021.model.core;

import static java.util.Collections.emptyList;

import io.vavr.Tuple2;
import lombok.Value;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
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

    public List<Entry> getAllAdjacent(Coordinate coordinate) {
        return Stream.of(
                getEntryIfExists(coordinate.top()),
                getEntryIfExists(coordinate.topRight()),
                getEntryIfExists(coordinate.right()),
                getEntryIfExists(coordinate.bottomRight()),
                getEntryIfExists(coordinate.bottom()),
                getEntryIfExists(coordinate.bottomLeft()),
                getEntryIfExists(coordinate.left()),
                getEntryIfExists(coordinate.topLeft())
            )
            .flatMap(Optional::stream)
            .collect(Collectors.toList());
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
            .flatMap(Optional::stream)
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

    public boolean isInBound(Coordinate coordinate) {
        return isInBound(coordinate.getX(), coordinate.getY());
    }

    public boolean isInBound(int x, int y) {
        if (0 <= y && y < matrix.length) {
            return 0 <= x && x < matrix[y].length;
        }
        return false;
    }

    public Optional<Entry> getEntryIfExists(Coordinate coordinate) {
        if (isInBound(coordinate)) {
            return Optional.of(new Entry(coordinate, getValue(coordinate)));
        }
        return Optional.empty();
    }

    public Optional<Entry> getEntryIfExists(int x, int y) {
        if (isInBound(x, y)) {
            final var coord = Coordinate.of(x, y);
            return Optional.of(new Entry(coord, getValue(coord)));
        }
        return Optional.empty();
    }

    public T set(Coordinate coordinate, Function<T, T> function) {
        if (isInBound(coordinate)) {
            final var currentValue = this.matrix[coordinate.getY()][coordinate.getX()];
            matrix[coordinate.getY()][coordinate.getX()] = function.apply(currentValue);
            return matrix[coordinate.getY()][coordinate.getX()];
        }
        return null;
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

    public Stream<Coordinate> streamCoordinates() {
        return IntStream
            .range(0, matrix.length)
            .boxed()
            .flatMap(y -> IntStream.range(0, matrix[y].length).mapToObj(x -> Coordinate.of(x, y)));
    }

    public Matrix<T> transposeVertical() {
        final var height = getHeight();
        final T[][] newMatrix = newInstance();
        stream().forEach(entry -> {
            final var c = entry.getCoordinate();
            newMatrix[(height - c.getY()) - 1][c.getX()] = entry.getValue();
        });
        return new Matrix<>(newMatrix);
    }

    public Matrix<T> transposeHorizontally() {
        final var width = getWidth();
        final T[][] newMatrix = newInstance();
        stream().forEach(entry -> {
            final var c = entry.getCoordinate();
            newMatrix[c.getY()][(width - c.getX()) - 1] = entry.getValue();
        });
        return new Matrix<>(newMatrix);
    }


    public Matrix<T> merge(Matrix<T> other, BiFunction<T, T, T> mergeValues) {
        final T[][] newMatrix = newInstance();
        stream().forEach(entry -> {
            final var c = entry.getCoordinate();
            newMatrix[c.getY()][c.getX()] = mergeValues.apply(entry.getValue(), other.getValue(c));
        });
        return new Matrix<>(newMatrix);
    }

    public Tuple2<Matrix<T>, Matrix<T>> splitHorizontallyAt(int y) {
        final T[][] matrix1 = newInstance(getWidth(), y);
        final T[][] matrix2 = newInstance(getWidth(), getHeight() - y - 1);
        for (int i = 0; i < getWidth(); ++i) {
            for (int j = 0; j < getHeight(); ++j) {
                if (j < y) {
                    matrix1[j][i] = matrix[j][i];
                } else if (j > y) {
                    matrix2[j - y - 1][i] = matrix[j][i];
                }
            }
        }
        return new Tuple2<>(new Matrix<>(matrix1), new Matrix<>(matrix2));
    }

    public Tuple2<Matrix<T>, Matrix<T>> splitVerticallyAt(int x) {
        final T[][] matrix1 = newInstance(x, getHeight());
        final T[][] matrix2 = newInstance(getWidth() - x -1, getHeight());
        for (int i = 0; i < getWidth(); ++i) {
            for (int j = 0; j < getHeight(); ++j) {
                if (i < x) {
                    matrix1[j][i] = matrix[j][i];
                } else if (i > x) {
                    matrix2[j][i - x - 1] = matrix[j][i];
                }
            }
        }
        return new Tuple2<>(new Matrix<>(matrix1), new Matrix<>(matrix2));
    }


    private T[][] newInstance() {
        return newInstance(getWidth(), getHeight());
    }

    private int getHeight() {
        return matrix.length;
    }

    private int getWidth() {
        return matrix[0].length;
    }

    @SuppressWarnings("unchecked")
    private T[][] newInstance(int width, int height) {
        return (T[][]) Array.newInstance(matrix[0][0].getClass(), height, width);
    }

    @Override
    public String toString() {
        final var builder = new StringBuilder();
        for (T[] ts : matrix) {
            for (T t : ts) {
                builder.append(t);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
