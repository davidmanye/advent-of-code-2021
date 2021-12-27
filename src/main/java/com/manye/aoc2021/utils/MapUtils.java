package com.manye.aoc2021.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class MapUtils {

    public static <K, V> Map<K, V> merge(Map<K, V> map1, Map<K, V> map2, BiFunction<V, V, V> merge) {
        final Map<K, V> result = new HashMap<>(map1);
        map2.forEach((key, value) -> {
            if (result.containsKey(key)) {
                result.put(key, merge.apply(result.get(key), value));
            } else {
                result.put(key, value);
            }
        });
        return result;
    }
}
