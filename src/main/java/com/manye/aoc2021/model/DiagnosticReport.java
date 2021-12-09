package com.manye.aoc2021.model;

import static com.google.common.primitives.Ints.asList;

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

    public int getNumberOfRows() {
        return binaryTable.length;
    }

    public DiagnosticReport keep(int bit, int column) {
        final List<int[]> newReport = new ArrayList<>();
        for (int[] row : binaryTable) {
            if (row[column] == bit) {
                newReport.add(row);
            }
        }
        return new DiagnosticReport(newReport.toArray(int[][]::new));
    }

    public List<Integer> getRow(int row) {
        return asList(binaryTable[row]);
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
