package com.manye.aoc2021.utils;

import static java.util.function.Function.identity;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ListUtils {

    public static <T> T mostCommon(List<T> elements) {
        var grouped = elements.stream().collect(Collectors.groupingBy(identity()));
        final Comparator<Map.Entry<T, List<T>>> comparing = Comparator.comparing(entry -> entry.getValue().size());
        return grouped
            .entrySet()
            .stream()
            .max(comparing)
            .map(Map.Entry::getKey)
            .orElseThrow();
    }

    public static <T> T lessCommon(List<T> elements) {
        var grouped = elements.stream().collect(Collectors.groupingBy(identity()));
        final Comparator<Map.Entry<T, List<T>>> comparing = Comparator.comparing(entry -> entry.getValue().size());
        return grouped
            .entrySet()
            .stream()
            .min(comparing)
            .map(Map.Entry::getKey)
            .orElseThrow();
    }
}
