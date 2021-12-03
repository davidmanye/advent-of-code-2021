package com.manye.aoc2021.model;

import static java.util.stream.Collectors.joining;

import java.util.List;

public class BinaryRate {

    private final List<Integer> binary;

    public BinaryRate(List<Integer> binary) {
        this.binary = binary;
    }

    public String getAsBinary() {
        return binary.stream().map(Object::toString).collect(joining(""));
    }

    public int getAsInt() {
        return Integer.parseInt(getAsBinary(), 2);
    }
}
