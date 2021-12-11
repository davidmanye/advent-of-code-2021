package com.manye.aoc2021.model;

import com.manye.aoc2021.model.core.Coordinate;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class VentDiagram {

    private final int[][] points;

    public VentDiagram(int width, int height) {
        this.points = new int[width][height];
    }

    public long countCellsMatch(IntPredicate matcher) {
        return Arrays
            .stream(points)
            .flatMapToInt(Arrays::stream)
            .filter(matcher)
            .count();
    }

    public void markCoordinates(List<Coordinate> coordinates) {
        for (Coordinate coordinate: coordinates) {
            markCoordinate(coordinate);
        }
    }

    public void markCoordinate(Coordinate coordinate) {
        points[coordinate.getX()][coordinate.getY()] += 1;
    }
}
