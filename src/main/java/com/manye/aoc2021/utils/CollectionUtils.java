package com.manye.aoc2021.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils {

    public static <T, C extends Collection<T>> C add(C list, T element) {
        list.add(element);
        return list;
    }

    public static <T> List<T> addImmutableList(List<T> list, T element) {
        final var result = new ArrayList<>(list);
        result.add(element);
        return result;
    }

    public static <T> Set<T> addImmutableSet(Set<T> list, T element) {
        final var result = new HashSet<>(list);
        result.add(element);
        return result;
    }

    public static <T> List<T> merge(List<T> list1, List<T> list2) {
        final var result = new ArrayList<T>(list1);
        result.addAll(list2);
        return result;
    }

    public static <T> Set<T> merge(Set<T> list1, Set<T> list2) {
        final var result = new HashSet<T>(list1);
        result.addAll(list2);
        return result;
    }
}
