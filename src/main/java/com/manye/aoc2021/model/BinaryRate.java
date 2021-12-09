package com.manye.aoc2021.model;

import static java.util.stream.Collectors.joining;

import java.util.List;

public class BinaryRate {

    public static final Integer ZERO = 0;
    public static final Integer ONE = 1;

    enum Type {
        GAMMA,
        EPSILON,
        OXYGEN_GENERATOR,
        CO2_SCRUBBER
    }

    private final List<Integer> binary;
    private final Type type;

    public BinaryRate(List<Integer> binary, Type type) {
        this.binary = binary;
        this.type = type;
    }

    public String getAsBinary() {
        return binary.stream().map(Object::toString).collect(joining(""));
    }

    public int getAsInt() {
        return Integer.parseInt(getAsBinary(), 2);
    }
}
