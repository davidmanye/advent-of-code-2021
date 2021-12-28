package com.manye.aoc2021.model.polymer;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingLong;
import static java.util.stream.Collectors.toMap;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class Polymer {

    private Map<Pair<Character, Character>, Long> pairs;
    private final Map<Pair<Character, Character>, List<Pair<Character, Character>>> instructions;

    public static Polymer fromInput(String input) {
        var split = input.split("\n\n");
        return Polymer.builder()
            .pairs(pairs(split[0]))
            .instructions(Arrays.stream(split[1].split("\n"))
                .map(line -> line.split(" -> "))
                .collect(toMap(line -> {
                    var chars = line[0].toCharArray();
                    return new ImmutablePair<>(chars[0], chars[1]);
                }, line -> {
                    var chars = line[0].toCharArray();
                    return List.of(new ImmutablePair<>(chars[0], line[1].charAt(0)), new ImmutablePair<>(line[1].charAt(0), chars[1]));
                })))
            .build();
    }

    private static Map<Pair<Character, Character>, Long> pairs(String input) {
        return IntStream.range(0, input.length() - 1)
            .mapToObj(i -> new ImmutablePair<>(input.charAt(i), input.charAt(i + 1)))
            .collect(groupingBy(identity(), counting()));
    }

    public void doInsertion(int times) {
        IntStream.range(0, times).forEach(x -> doInsertion());
    }

    public void doInsertion() {
        pairs = pairs.entrySet()
            .stream()
            .flatMap(e -> instructions.get(e.getKey()).stream().map(k -> Map.entry(k, e.getValue())))
            .collect(groupingBy(Map.Entry::getKey, HashMap::new, summingLong(Map.Entry::getValue)));
    }

    public Map<Character, Long> countOccurrencesIgnoringFirst() {
        return pairs.entrySet().stream()
            .map(e -> Map.entry(e.getKey().getRight(), e.getValue()))
            .collect(groupingBy(Map.Entry::getKey, HashMap::new, summingLong(Map.Entry::getValue)));
    }

    public long getMagicNumber() {
        var statistics = countOccurrencesIgnoringFirst().values().stream().mapToLong(Long::longValue).summaryStatistics();
        return statistics.getMax() - statistics.getMin();
    }
}
