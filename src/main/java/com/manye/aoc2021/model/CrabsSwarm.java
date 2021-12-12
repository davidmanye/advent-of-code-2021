package com.manye.aoc2021.model;

import java.util.Arrays;
import java.util.stream.IntStream;

public class CrabsSwarm {

    public enum FuelCost {
        CONSTANT,
        INCREMENTAL
    }

    private final FuelCost fuelCost;
    private final int[] crabs;

    public CrabsSwarm(int[] crabs) {
        this(crabs, FuelCost.CONSTANT);
    }

    public CrabsSwarm(int[] crabs, FuelCost cost) {
        this.crabs = crabs;
        this.fuelCost = cost;
    }

    public int calculateFuelAtPosition(int position) {
        return Arrays
            .stream(crabs)
            .map(crab -> move(crab, position))
            .sum();
    }

    public int move(int start, int end) {
        switch (fuelCost) {
            case CONSTANT:
                return Math.abs(start - end);
            case INCREMENTAL:
                return IntStream.rangeClosed(1, Math.abs(start - end)).sum();
        }
        throw new RuntimeException("No fuel cost: " + this.fuelCost);
    }

    public int calculateBestFuel() {
        final var max = Arrays.stream(crabs).max().orElseThrow();
        return IntStream.rangeClosed(0, max)
            .distinct()
            .map(this::calculateFuelAtPosition)
            .min()
            .orElseThrow();
    }
}
