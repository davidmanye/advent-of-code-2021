package com.manye.aoc2021.model.core;

import lombok.Value;

@Value
public class Coordinate {

    int x;
    int y;

    public Coordinate sum(int x, int y) {
        return new Coordinate(this.x + x, this.y + y);
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }
}
