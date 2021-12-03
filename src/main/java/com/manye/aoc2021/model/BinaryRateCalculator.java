package com.manye.aoc2021.model;

import com.manye.aoc2021.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BinaryRateCalculator {

    public static BinaryRate getGammaRate(DiagnosticReport report) {
        return getBinaryRate(report, ListUtils::mostCommon);
    }

    public static BinaryRate getEpsilonRate(DiagnosticReport report) {
        return getBinaryRate(report, ListUtils::lessCommon);
    }

    private static BinaryRate getBinaryRate(DiagnosticReport report, Function<List<Integer>, Integer> findBit) {
        final var binary = new ArrayList<Integer>();
        for (int i = 0; i < report.getNumberOfColumns(); ++i) {
            var column = report.getColumn(i);
            var bit = findBit.apply(column);
            binary.add(bit);
        }
        return new BinaryRate(binary);
    }
}
