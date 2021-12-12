package com.manye.aoc2021.model;

import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.Matrix;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HeightMap {

    private Matrix<Integer> heights;

    public HeightMap(Integer[][] heights) {
        this.heights = new Matrix<>(heights);
    }

    public Stream<Matrix<Integer>.Entry> getLowPoints() {
        return heights
            .stream()
            .filter(entry -> {
                final var point = entry.getValue();
                final var adjacents = heights.getAdjacentValues(entry.getCoordinate());
                return adjacents.stream().allMatch(adjacent -> adjacent > point);
            });
    }

    public List<Integer> getLowPointsValues() {
        return getLowPoints()
            .map(Matrix.Entry::getValue)
            .collect(Collectors.toList());
    }

    public List<Matrix<Integer>.Entry> getBasis(Coordinate coordinate) {
        return heights.searchValuesFrom(coordinate, value -> value < 9);
    }

    public List<Integer> getRiskLevelOfLowPoints() {
        return getLowPointsValues()
            .stream()
            .map(lowPoint -> lowPoint + 1)
            .collect(Collectors.toList());
    }
}
