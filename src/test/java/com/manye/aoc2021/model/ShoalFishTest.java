package com.manye.aoc2021.model;

import static com.manye.aoc2021.model.ShoalFish.LANTERN_FISH_NEW_BORN_DAY;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ShoalFishTest {

    @ParameterizedTest
    @MethodSource("provideDays")
    void getFishesWith(int days, int[] fishes) {
        final var shoal = new ShoalFish(ShoalFish.LANTERN_FISH_CYCLE, LANTERN_FISH_NEW_BORN_DAY);
        shoal.init(new int[] {3, 4, 3, 1, 2});

        IntStream
            .range(0, days)
            .forEach(day -> shoal.next());

        final var grouped = Arrays.stream(fishes).boxed().collect(Collectors.groupingBy(Function.identity()));
        final var expectedShoal = new long[LANTERN_FISH_NEW_BORN_DAY + 1];
        grouped.forEach((day, list) -> expectedShoal[day] = list.size());
        assertThat(shoal.getShoal())
            .containsExactly(expectedShoal);
    }

    private static Stream<Arguments> provideDays() {
        return Stream.of(
            Arguments.of(1, new int[] {2, 3, 2, 0, 1}),
            Arguments.of(2, new int[] {1, 2, 1, 6, 0, 8}),
            Arguments.of(3, new int[] {0, 1, 0, 5, 6, 7, 8}),
            Arguments.of(10, new int[] {0, 1, 0, 5, 6, 0, 1, 2, 2, 3, 7, 8})
        );
    }
}