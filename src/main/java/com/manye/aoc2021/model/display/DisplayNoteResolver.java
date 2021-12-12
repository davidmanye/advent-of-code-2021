package com.manye.aoc2021.model.display;

import java.util.stream.Collectors;

public class DisplayNoteResolver {

    public static long resolve(DisplayNote displayNote) {
        final var configuration = WireConfiguration.from(displayNote.getSignalPatterns());
        final var conversor = new DisplayConversor(configuration);
        final var number = displayNote.getOutputValue().stream().map(conversor::convert).collect(Collectors.joining(""));
        return Long.parseLong(number);
    }
}
