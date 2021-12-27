package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.transparentpaper.FoldInstruction;
import com.manye.aoc2021.model.transparentpaper.TransparentPaper;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day13 {

    public static void main(String[] args) {
        final var all = InputReader.readAll("/Day13/input.txt");
        final var split = all.split("\n\n");
        final var transparentPaper = Day13.readMatrix(split[0]);
        final var instructions = Day13.readInstructions(split[1]);

        System.out.println("Part 1: " + part1(transparentPaper, instructions));
        System.out.println("Part 2:\n" + part2(transparentPaper, instructions));
    }

    private static String part2(TransparentPaper transparentPaper, List<FoldInstruction> instructions) {
        instructions.stream().skip(1).forEach(transparentPaper::apply);
        return transparentPaper.toString();
    }

    public static TransparentPaper readMatrix(String coordinatesBlock) {
        final var coordinates = Arrays.stream(coordinatesBlock.split("\n"))
            .map(line -> line.split(","))
            .map(splits -> Coordinate.of(Integer.parseInt(splits[0]), Integer.parseInt(splits[1])))
            .collect(Collectors.toList());
        return TransparentPaper.from(coordinates);
    }

    public static List<FoldInstruction> readInstructions(String instructionsBlock) {
        final var pattern = Pattern.compile("([xy])=([\\d]+)");
        return Arrays.stream(instructionsBlock.split("\n"))
            .map(line -> {
                final var matcher = pattern.matcher(line);
                if (matcher.find()) {
                    return new FoldInstruction(FoldInstruction.Type.valueOf(matcher.group(1).toUpperCase()), Integer.parseInt(matcher.group(2)));
                }
                return null;
            })
            .collect(Collectors.toList());
    }

    public static long part1(TransparentPaper transparentPaper, List<FoldInstruction> instructions) {
        final var instruction = instructions.get(0);
        transparentPaper.apply(instruction);
        return transparentPaper.getNumberOfDots();
    }
}
