package com.manye.aoc2021.days;

import static com.manye.aoc2021.model.ShoalFish.LANTERN_FISH_CYCLE;
import static com.manye.aoc2021.model.ShoalFish.LANTERN_FISH_NEW_BORN_DAY;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.ShoalFish;

import java.util.stream.IntStream;

public class Day06 {

    public static void main(String[] args) {
        final var fishes = InputReader.readAsIntArray("/Day06/input.txt");

        System.out.println("Part 1: " + part1(fishes));
        System.out.println("Part 2: " + part2(fishes));
    }

    public static long part1(int[] fishes) {
        return afterDays(fishes, 80);
    }

    public static long part2(int[] fishes) {
        return afterDays(fishes, 256);
    }

    private static long afterDays(int[] fishes, int days) {
        final var shoal = new ShoalFish(LANTERN_FISH_CYCLE, LANTERN_FISH_NEW_BORN_DAY);
        shoal.init(fishes);

        IntStream
            .range(0, days)
            .forEach(day -> shoal.next());

        return shoal.countAllFishes();
    }
}
