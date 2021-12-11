package com.manye.aoc2021.model;

import java.util.Arrays;

public class ShoalFish {

    public static final int LANTERN_FISH_CYCLE = 7;
    public static final int LANTERN_FISH_NEW_BORN_DAY = 8;

    private final int cycle;
    private final int newBornDay;
    private int currentDay = 0;
    private long[] shoal;

    public ShoalFish(int cycle, int newBornDay) {
        this.cycle = cycle;
        this.newBornDay = newBornDay;
        this.shoal = new long[newBornDay + 1];
    }

    public long[] getShoal() {
        return shoal;
    }

    public void init(int[] initialState) {
        for (int i: initialState) {
            shoal[i] += 1;
        }
    }

    public void next() {
        final var newShoal = new long[shoal.length];
        final var fishesAtDay = shoal[0];
        newShoal[getResetIndexCycle()] = fishesAtDay;
        newShoal[newBornDay] = fishesAtDay;
        for (int day = 1; day < shoal.length; ++day) {
            newShoal[day - 1] += shoal[day];
        }
        shoal = newShoal;
        ++currentDay;
    }

    public long countAllFishes() {
        return Arrays.stream(shoal).sum();
    }

    private int getResetIndexCycle() {
        return cycle - 1;
    }
}
