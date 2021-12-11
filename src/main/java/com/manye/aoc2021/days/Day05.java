package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.VentDiagram;
import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.LineSegment;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day05 {

    public static void main(String[] args) {
        final var lineSegments = InputReader.readLineSegments("/Day05/input.txt");

        System.out.println("Part 1: " + part1(lineSegments));
        System.out.println("Part 2: " + part2(lineSegments));

    }

    public static long part1(List<LineSegment> lineSegments) {
        final List<LineSegment> validSegments = lineSegments
            .stream()
            .filter(segment -> segment.isHorizontal() || segment.isVertical())
            .collect(Collectors.toList());

        return part2(validSegments);
    }

    private static long part2(List<LineSegment> validSegments) {
        final var width = getWidthFrom(validSegments);
        final var height = getHeightFrom(validSegments);
        final var diagram = new VentDiagram(width + 1, height + 1);

        validSegments
            .stream()
            .flatMap(lineSegment -> lineSegment.getAllCoordinates().stream())
            .forEach(diagram::markCoordinate);

        return diagram.countCellsMatch(number -> number > 1);
    }

    private static int getWidthFrom(List<LineSegment> validSegments) {
        return validSegments.stream()
            .flatMap(segment -> Stream.of(segment.getStart(), segment.getEnd()))
            .mapToInt(Coordinate::getX)
            .max()
            .orElseThrow();
    }

    private static int getHeightFrom(List<LineSegment> validSegments) {
        return validSegments.stream()
            .flatMap(segment -> Stream.of(segment.getStart(), segment.getEnd()))
            .mapToInt(Coordinate::getX)
            .max()
            .orElseThrow();
    }
}
