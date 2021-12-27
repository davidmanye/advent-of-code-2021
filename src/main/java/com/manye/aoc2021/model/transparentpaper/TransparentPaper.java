package com.manye.aoc2021.model.transparentpaper;

import com.manye.aoc2021.model.core.Coordinate;
import com.manye.aoc2021.model.core.Matrix;

import io.vavr.Tuple2;

import java.util.List;

public class TransparentPaper {

    public static final String DOT = "#";
    public static final String BLANK = ".";

    private Matrix<String> paper;

    public static TransparentPaper from(List<Coordinate> coordinates) {
        final var maxX = coordinates.stream().mapToInt(Coordinate::getX).max().orElseThrow();
        final var maxY = coordinates.stream().mapToInt(Coordinate::getY).max().orElseThrow();
        final var matrix = new Matrix<>(new String[maxY + 1][maxX + 1], BLANK);
        coordinates.forEach(coordinate -> matrix.set(coordinate, (current) -> DOT));
        return new TransparentPaper(matrix);
    }

    private TransparentPaper(Matrix<String> paper) {
        this.paper = paper;
    }

    public long getNumberOfDots() {
        return paper.stream().filter(entry -> DOT.equals(entry.getValue())).count();
    }

    public void apply(FoldInstruction instruction) {
        final Tuple2<Matrix<String>, Matrix<String>> halves = splitPaper(instruction);
        final Matrix<String> transposedMatrix = transposeMatrix(instruction, halves);
        this.paper = halves._1.merge(transposedMatrix, (v1, v2) -> DOT.equals(v1) ? v1 : v2);
    }

    private Matrix<String> transposeMatrix(FoldInstruction instruction, Tuple2<Matrix<String>, Matrix<String>> halves) {
        switch (instruction.getType()) {
            case X:
                return halves._2.transposeHorizontally();
            case Y:
                return halves._2.transposeVertical();
            default:
                throw new IllegalStateException("Not found: " + instruction);
        }
    }

    private Tuple2<Matrix<String>, Matrix<String>> splitPaper(FoldInstruction instruction) {
        switch (instruction.getType()) {
            case X:
                return paper.splitVerticallyAt(instruction.getValue());
            case Y:
                return paper.splitHorizontallyAt(instruction.getValue());
            default:
                throw new IllegalStateException("Not found: " + instruction);
        }
    }

    @Override
    public String toString() {
        return paper.toString();
    }
}
