package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.CrabsSwarm;
import com.manye.aoc2021.model.CrabsSwarm.FuelCost;

public class Day07 {

    public static void main(String[] args) {
        final var positions = InputReader.readAsIntArray("/Day07/input.txt");

        System.out.println("Part 1: " + part1(positions));
        System.out.println("Part 2: " + part2(positions));
    }

    public static int part1(int[] positions) {
        final var crabsSwarm = new CrabsSwarm(positions);
        return crabsSwarm.calculateBestFuel();
    }

    public static int part2(int[] positions) {
        final var crabsSwarm = new CrabsSwarm(positions, FuelCost.INCREMENTAL);
        return crabsSwarm.calculateBestFuel();
    }
}
