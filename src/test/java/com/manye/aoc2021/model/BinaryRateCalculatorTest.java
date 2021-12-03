package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class BinaryRateCalculatorTest {

    @Test
    void calculateGammaRateWithMostCommonBitsOfColumns() {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/example.txt");

        var gammaRate = BinaryRateCalculator.getGammaRate(diagnosticReport);

        assertThat(gammaRate.getAsInt()).isEqualTo(22);
    }

    @Test
    void calculateEpsilonRateWithLessCommonBitsOfColumns() {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/example.txt");

        var gammaRate = BinaryRateCalculator.getEpsilonRate(diagnosticReport);

        assertThat(gammaRate.getAsInt()).isEqualTo(9);
    }
}