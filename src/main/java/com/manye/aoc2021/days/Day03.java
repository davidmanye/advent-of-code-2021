package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.BinaryRateCalculator;
import com.manye.aoc2021.model.DiagnosticReport;

public class Day03 {

    public static void main(String[] args) {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/input.txt");

        System.out.println("Part 1: " + part1(diagnosticReport));
    }

    public static int part1(DiagnosticReport diagnosticReport) {
        var gamma = BinaryRateCalculator.getGammaRate(diagnosticReport);
        var epsilon = BinaryRateCalculator.getEpsilonRate(diagnosticReport);
        return gamma.getAsInt() * epsilon.getAsInt();
    }
}
