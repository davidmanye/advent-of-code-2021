package com.manye.aoc2021.model;

import java.util.ArrayList;
import java.util.List;

public class DiagnosticReport {

    private final int[][] binaryTable;

    public DiagnosticReport(int[][] binaryTable) {
        this.binaryTable = binaryTable;
    }

    public List<Integer> getColumn(int columnIndex) {
        final var column = new ArrayList<Integer>();
        for (int[] row : binaryTable) {
            column.add(row[columnIndex]);
        }
        return column;
    }

    public int getNumberOfColumns() {
        return binaryTable[0].length;
    }

    @Override
    public String toString() {
        var builder = new StringBuilder("DiagnosticReport{\n");
        for (int[] row : binaryTable) {
            for (int cell : row) {
                builder.append(cell);
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
