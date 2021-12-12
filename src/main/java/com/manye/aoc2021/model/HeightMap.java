package com.manye.aoc2021.model;

import com.manye.aoc2021.model.core.Matrix;

import java.util.List;
import java.util.stream.Collectors;

public class HeightMap {

    private Matrix<Integer> heights;

    public HeightMap(Integer[][] heights) {
        this.heights = new Matrix<>(heights);
    }

    public List<Integer> getLowPoints() {
        return heights
            .stream()
            .filter(entry -> {
                final var point = entry.getValue();
                final var adjacents = heights.getAdjacentValues(entry.getCoordinate());
                return adjacents.stream().allMatch(adjacent -> adjacent > point);
            })
            .map(Matrix.Entry::getValue)
            .collect(Collectors.toList());
    }

    public List<Integer> getRiskLevelOfLowPoints() {
        return getLowPoints()
            .stream()
            .map(lowPoint -> lowPoint + 1)
            .collect(Collectors.toList());
    }
}
