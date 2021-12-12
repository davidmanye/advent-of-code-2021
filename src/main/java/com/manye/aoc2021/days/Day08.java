package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.display.DisplayNote;
import com.manye.aoc2021.model.display.DisplayNoteResolver;

import java.util.List;
import java.util.Set;

public class Day08 {

    public static void main(String[] args) {
        final var displayNotes = InputReader.readDisplayNotes("/Day08/input.txt");

        System.out.println("Part 1: " + part1(displayNotes));
        System.out.println("Part 2: " + part2(displayNotes));

    }

    public static long part1(List<DisplayNote> notes) {
        final Set<Integer> uniqueSize = Set.of(2, 3, 4, 7);
        return notes
            .stream()
            .flatMap(note -> note.getOutputValue().stream())
            .map(String::length)
            .filter(uniqueSize::contains)
            .count();
    }

    public static long part2(List<DisplayNote> notes) {
        return notes
            .stream()
            .mapToLong(DisplayNoteResolver::resolve)
            .sum();
    }
}
