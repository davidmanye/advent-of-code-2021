package com.manye.aoc2021.model;

import com.manye.aoc2021.utils.BinaryListUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BinaryRateCalculator {

    public static BinaryRate getGammaRate(DiagnosticReport report) {
        return getSimpleBinaryRate(report, BinaryListUtils::mostCommon, BinaryRate.Type.GAMMA);
    }

    public static BinaryRate getEpsilonRate(DiagnosticReport report) {
        return getSimpleBinaryRate(report, BinaryListUtils::lessCommon, BinaryRate.Type.EPSILON);
    }

    public static BinaryRate getOxygenGenerator(DiagnosticReport report) {
        return getComplexBinaryRate(report, BinaryListUtils::mostCommon, BinaryRate.ONE, BinaryRate.Type.OXYGEN_GENERATOR);
    }

    public static BinaryRate getC02Scrubber(DiagnosticReport report) {
        return getComplexBinaryRate(report, BinaryListUtils::lessCommon, BinaryRate.ZERO, BinaryRate.Type.CO2_SCRUBBER);
    }

    private static BinaryRate getSimpleBinaryRate(DiagnosticReport report, Function<List<Integer>, Integer> findBit, BinaryRate.Type type) {
        final var binary = new ArrayList<Integer>();
        for (int i = 0; i < report.getNumberOfColumns(); ++i) {
            final var column = report.getColumn(i);
            final var bit = findBit.apply(column);
            binary.add(bit);
        }
        return new BinaryRate(binary, type);
    }

    private static BinaryRate getComplexBinaryRate(DiagnosticReport report, Function<List<Integer>, Integer> findBit, Integer bitLast, BinaryRate.Type type) {
        var resultingReport = report;
        for (int i = 0; i < report.getNumberOfColumns(); ++i) {
            final var column = resultingReport.getColumn(i);
            final var bit = findBit.apply(column);
            if (bit == -1) {
                resultingReport = resultingReport.keep(bitLast, i);
                if (resultingReport.getNumberOfRows() == 1) {
                    return new BinaryRate(resultingReport.getRow(0), type);
                }
            } else {
                resultingReport = resultingReport.keep(bit, i);
            }
        }
        throw new RuntimeException("Not found a result");
    }
}
