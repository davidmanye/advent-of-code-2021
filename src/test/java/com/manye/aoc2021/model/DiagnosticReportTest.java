package com.manye.aoc2021.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.manye.aoc2021.input.InputReader;

import org.junit.jupiter.api.Test;

class DiagnosticReportTest {

    @Test
    void getColumn() {
        var diagnosticReport = InputReader.readDiagnosticReport("/Day03/example.txt");

        var column = diagnosticReport.getColumn(0);

        assertThat(column).containsExactly(0,1,1,1,1,0,0,1,1,1,0,0);
    }
}