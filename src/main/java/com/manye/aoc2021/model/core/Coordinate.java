package com.manye.aoc2021.model.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(of = { "x", "y" })
public class Coordinate {

    int x;
    int y;

    public Coordinate sum(int x, int y) {
        return new Coordinate(this.x + x, this.y + y);
    }

    public Coordinate top() {
        return Coordinate.of(x, y - 1);
    }

    public Coordinate right() {
        return Coordinate.of(x + 1, y);
    }

    public Coordinate left() {
        return Coordinate.of(x - 1, y);
    }

    public Coordinate bottom() {
        return Coordinate.of(x, y + 1);
    }

    public Coordinate topLeft() {
        return Coordinate.of(x - 1, y - 1);
    }

    public Coordinate topRight() {
        return Coordinate.of(x + 1, y - 1);
    }

    public Coordinate bottomLeft() {
        return Coordinate.of(x - 1, y + 1);
    }

    public Coordinate bottomRight() {
        return Coordinate.of(x + 1, y + 1);
    }

    public static Coordinate of(int x, int y) {
        return new Coordinate(x, y);
    }
}
