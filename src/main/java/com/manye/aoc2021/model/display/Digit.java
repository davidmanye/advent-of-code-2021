package com.manye.aoc2021.model.display;

import java.util.Arrays;

public enum Digit {

    ZERO(new int[] {1,1,1,0,1,1,1}),
    ONE(new int[] {0,0,1,0,0,1,0}),
    TWO(new int[] {1,0,1,1,1,0,1}),
    THREE(new int[] {1,0,1,1,0,1,1}),
    FOUR(new int[] {0,1,1,1,0,1,0}),
    FIVE(new int[] {1,1,0,1,0,1,1}),
    SIX(new int[] {1,1,0,1,1,1,1}),
    SEVEN(new int[] {1,0,1,0,0,1,0}),
    EIGHT(new int[] {1,1,1,1,1,1,1}),
    NINE(new int[] {1,1,1,1,0,1,1});

    private final int[] on;

    Digit(int[] on) {
        this.on = on;
    }

    public static Digit from(int[] on) {
        for(Digit digit: Digit.values()) {
            if (Arrays.equals(digit.on, on)) {
                return digit;
            }
        }
        throw new IllegalStateException("Not found digit");
    }

    public static String toNumber(Digit digit) {
        switch (digit) {
            case ZERO:
                return "0";
            case ONE:
                return "1";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
        }
        throw new IllegalStateException("Not here: " + digit);
    }
}
