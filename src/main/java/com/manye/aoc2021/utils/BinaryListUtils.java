package com.manye.aoc2021.utils;

import static com.manye.aoc2021.model.BinaryRate.ONE;
import static com.manye.aoc2021.model.BinaryRate.ZERO;
import static java.util.Collections.emptyList;
import static java.util.function.Function.identity;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public final class BinaryListUtils {

    public static final BiFunction<Integer, Integer, Integer> MAX = (zeroSize, oneSize) -> zeroSize > oneSize ? ZERO : ONE;
    public static final BiFunction<Integer, Integer, Integer> MIN = (zeroSize, oneSize) -> zeroSize < oneSize ? ZERO : ONE;

    public static Integer mostCommon(List<Integer> elements) {
        return common(elements, MAX);
    }

    public static Integer lessCommon(List<Integer> elements) {
        return common(elements, MIN);
    }

    private static Integer common(List<Integer> elements, BiFunction<Integer, Integer, Integer> compare) {
        var grouped = elements.stream().collect(Collectors.groupingBy(identity()));
        final int zeroSize = grouped.getOrDefault(ZERO, emptyList()).size();
        final int oneSize = grouped.getOrDefault(ONE, emptyList()).size();
        if (zeroSize == oneSize) {
            return -1;
        }
        return compare.apply(zeroSize, oneSize);
    }
}
