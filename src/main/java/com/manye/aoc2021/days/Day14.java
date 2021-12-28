package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.polymer.Polymer;

public class Day14 {

    public static void main(String[] args) {
        final var polymer1 = InputReader.readPolymer("/Day14/input.txt");
        final var polymer2 = InputReader.readPolymer("/Day14/input.txt");

        System.out.println("Part 1: " + part1(polymer1));
        System.out.println("Part 2: " + part2(polymer2));
    }

    public static long part1(Polymer polymer) {
        polymer.doInsertion(10);

        return polymer.getMagicNumber();
    }

    public static long part2(Polymer polymer) {
        polymer.doInsertion(40);

        return polymer.getMagicNumber();
    }
}
