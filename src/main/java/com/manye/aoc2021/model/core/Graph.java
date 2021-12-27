package com.manye.aoc2021.model.core;

import static com.manye.aoc2021.utils.CollectionUtils.addImmutableList;
import static com.manye.aoc2021.utils.CollectionUtils.addImmutableSet;
import static java.util.function.Predicate.not;

import lombok.Value;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Value
public class Graph<T> {

    Map<T, Set<T>> nodes;

    public Set<List<T>> findAllPaths(T start, T end) {
        final Set<List<T>> paths = new HashSet<>();
        findAllPaths(start, end, (node, currentPath) -> true, List.of(start), paths, Set.of(start));
        return paths;
    }

    public Set<List<T>> findAllPaths(T start, T end, BiPredicate<T, List<T>> markAsVisited) {
        final Set<List<T>> paths = new HashSet<>();
        findAllPaths(start, end, markAsVisited, List.of(start), paths, Set.of(start));
        return paths;
    }

    public Set<List<T>> findAllPaths(T current, T end, BiPredicate<T, List<T>> markAsVisited, List<T> currentPath, Set<List<T>> paths, Set<T> visited) {
        if (currentPath.get(currentPath.size() - 1).equals(end)) {
            paths.add(currentPath);
            return paths;
        } else {
            return nodes
                .get(current)
                .stream()
                .filter(not(visited::contains))
                .flatMap(node -> findAllPaths(node, end, markAsVisited, addImmutableList(currentPath, node), paths, markAsVisited.test(node, currentPath) ? addImmutableSet(visited, node) : visited).stream())
                .collect(Collectors.toSet());
        }
    }
}
