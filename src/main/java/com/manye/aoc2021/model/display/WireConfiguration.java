package com.manye.aoc2021.model.display;

import static com.google.common.collect.Sets.difference;
import static java.util.Arrays.asList;

import com.google.common.collect.Sets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class WireConfiguration {

    private final Map<String, Integer> letterPosition;

    private WireConfiguration(Map<String, Integer> letterPosition) {
        this.letterPosition = letterPosition;
    }

    public int getPositionOf(String letter) {
        return letterPosition.get(letter);
    }

    public static WireConfiguration from(List<String> signalPatterns) {
        final var configuration = new String[7];
        final var one = findOne(signalPatterns);
        final var four = findFour(signalPatterns);
        final var seven = findSeven(signalPatterns);
        final var eight = findEight(signalPatterns);

        final var sizeFive = findAllWithSize(signalPatterns, 5);
        final var sizeSix = findAllWithSize(signalPatterns, 6);

        final var commonSizeFive = common(sizeFive);
        final var commonSizeSix = common(sizeSix);

        // P0 --> 7 - 1
        configuration[0] = differenceGetOne(asList(seven, one));
        // P3 --> common(S5, 4)
        configuration[3] = commonGetOne(join(sizeFive, four));
        // P4 --> (8 - 4 - 7) - (common(S5))
        configuration[4] = differenceGetOne(join(asList(eight, four, seven), commonSizeFive));
        // P6 --> common(S5) - R
        configuration[6] = differenceGetOne(join(commonSizeFive, currentConfig(configuration)));
        // P1 --> (4 - 1) - R
        configuration[1] = differenceGetOne(join(asList(four, one), currentConfig(configuration)));
        // P5 --> common(S6) - R
        configuration[5] = differenceGetOne(join(commonSizeSix, currentConfig(configuration)));
        // P2 --> 8 - R
        configuration[2] = getOne(difference(eight, currentConfig(configuration)));

        final var map = new HashMap<String, Integer>();
        for (int i = 0; i < configuration.length; ++i) {
            map.put(configuration[i], i);
        }

        return new WireConfiguration(map);
    }

    private static Set<String> currentConfig(String[] conf) {
        return Arrays.stream(conf)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());
    }

    private static Set<String> common(List<Set<String>> list) {
        return list
            .stream()
            .reduce(Sets::intersection)
            .orElseThrow();
    }

    private static List<Set<String>> join(Set<String> e1, Set<String> e2) {
        List<Set<String>> all = new ArrayList<>();
        all.add(e1);
        all.add(e2);
        return all;
    }

    private static List<Set<String>> join(List<Set<String>> list, Set<String> element) {
        List<Set<String>> all = new ArrayList<>(list);
        all.add(element);
        return all;
    }

    private static String commonGetOne(List<Set<String>> list) {
        return getOne(common(list));
    }

    private static String differenceGetOne(List<Set<String>> list) {
        final var common = list
            .stream()
            .reduce(Sets::difference)
            .orElseThrow();
        return getOne(common);
    }

    private static String getOne(Set<String> difference) {
        if (difference.size() != 1) {
            throw new IllegalStateException("No expected: " + difference);
        }
        return difference.stream().findFirst().orElseThrow();
    }

    private static Set<String> findOne(List<String> signalPatterns) {
        return findWithSize(signalPatterns, 2);
    }

    private static Set<String> findFour(List<String> signalPatterns) {
        return findWithSize(signalPatterns, 4);
    }

    private static Set<String> findSeven(List<String> signalPatterns) {
        return findWithSize(signalPatterns, 3);
    }

    private static Set<String> findEight(List<String> signalPatterns) {
        return findWithSize(signalPatterns, 7);
    }

    private static Set<String> findWithSize(List<String> signalPatterns, int size) {
        return signalPatterns
            .stream()
            .filter(signal -> signal.length() == size)
            .map(s -> s.split(""))
            .map(s -> new LinkedHashSet<>(asList(s)))
            .findFirst()
            .orElseThrow();
    }

    private static List<Set<String>> findAllWithSize(List<String> signalPatterns, int size) {
        return signalPatterns
            .stream()
            .filter(signal -> signal.length() == size)
            .map(s -> s.split(""))
            .map(s -> new LinkedHashSet<>(asList(s)))
            .collect(Collectors.toList());
    }
}
