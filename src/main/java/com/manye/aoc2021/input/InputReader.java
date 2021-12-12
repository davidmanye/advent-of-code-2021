package com.manye.aoc2021.input;

import static java.util.stream.Collectors.toList;

import com.manye.aoc2021.model.Command;
import com.manye.aoc2021.model.DiagnosticReport;
import com.manye.aoc2021.model.bingo.BingoBoard;
import com.manye.aoc2021.model.bingo.BingoSubsystem;
import com.manye.aoc2021.model.core.LineSegment;
import com.manye.aoc2021.model.display.DisplayNote;
import com.manye.aoc2021.utils.StreamUtils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class InputReader {

    private static final Pattern DIGIT_PATTERN = Pattern.compile("[\\d]+");

    public static int[] readAsIntArray(String resourcePath) {
        return readLines(resourcePath)
            .flatMap(line -> Arrays.stream(line.split(",")))
            .mapToInt(Integer::parseInt)
            .toArray();
    }

    public static List<Integer> readAsIntList(String resourcePath) {
        return readLines(resourcePath)
            .map(Integer::parseInt)
            .collect(toList());
    }

    public static List<Command> reasAsCommands(String resourcePath) {
        return readLines(resourcePath)
            .map(InputParser::parseCommand)
            .collect(toList());
    }

    public static Stream<String> readLines(String resourcePath) {
        return StreamUtils.fromIterator(IOUtils.lineIterator(getInputStream(resourcePath), StandardCharsets.UTF_8));
    }

    public static String readAll(String resourcePath) {
        try {
            return IOUtils.toString(InputReader.class.getResourceAsStream(resourcePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static BingoSubsystem readBingo(String resourcePath) {
        final var groups = readAll(resourcePath).split("\\n\\n");
        final var drawNumbers = Arrays.stream(groups[0].split(",")).map(Integer::parseInt).collect(toList());
        final var boards = new ArrayList<BingoBoard>();
        for (int g = 1; g < groups.length; ++g) {
            final String square = groups[g];
            boards.add(InputParser.parseBingoBoard(square));
        }
        return new BingoSubsystem(drawNumbers, boards);
    }

    public static List<LineSegment> readLineSegments(String resourcePath) {
        return readLines(resourcePath)
            .map(InputParser::parseLineSegment)
            .collect(Collectors.toList());
    }

    public static InputStream getInputStream(String resourcePath) {
        return InputReader.class.getResourceAsStream(resourcePath);
    }

    public static DiagnosticReport readDiagnosticReport(String resourcePath) {
        var table = readLines(resourcePath)
            .map(s -> s.chars().map(Character::getNumericValue).toArray())
            .toArray(int[][]::new);
        return new DiagnosticReport(table);
    }

    public static List<DisplayNote> readDisplayNotes(String resourcePath) {
        return readLines(resourcePath)
            .map(InputParser::parseDisplayNote)
            .collect(Collectors.toList());
    }

}
