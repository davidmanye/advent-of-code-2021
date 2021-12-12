package com.manye.aoc2021.model.display;

import java.util.Arrays;

public class DisplayConversor {

    private final WireConfiguration wireConfiguration;

    public DisplayConversor(WireConfiguration wireConfiguration) {
        this.wireConfiguration = wireConfiguration;
    }

    public String convert(String signal) {
        final var on = new int[7];
        Arrays.stream(signal.split(""))
            .forEach(letter -> {
                on[wireConfiguration.getPositionOf(letter)] = 1;
            });
        return Digit.toNumber(Digit.from(on));
    }
}
