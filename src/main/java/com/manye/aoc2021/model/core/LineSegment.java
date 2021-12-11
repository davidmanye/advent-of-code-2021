package com.manye.aoc2021.model.core;

import static java.util.stream.Collectors.toList;

import lombok.Value;

import java.util.List;
import java.util.stream.IntStream;

@Value
public class LineSegment {

    Coordinate start;
    Coordinate end;

    public boolean isHorizontal() {
        return start.getX() == end.getX();
    }

    public boolean isVertical() {
        return start.getY() == end.getY();
    }

    public List<Coordinate> getAllCoordinates() {
        var diffX = end.getX() - start.getX();
        var diffY = end.getY() - start.getY();

        return IntStream
            .rangeClosed(0, Math.max(Math.abs(diffX), Math.abs(diffY)))
            .mapToObj(incr -> start.sum(getIncr(incr, diffX), getIncr(incr, diffY)))
            .collect(toList());

    }

    private int getIncr(int incr, int diff) {
        if (diff == 0) {
            return 0;
        } else if (diff < 0) {
            return -1 * incr;
        } else {
            return incr;
        }
    }
}
