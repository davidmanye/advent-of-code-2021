package com.manye.aoc2021.days;

import static com.manye.aoc2021.utils.CollectionUtils.addImmutableList;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toSet;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.core.Graph;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day12 {

    public static final String START = "start";
    public static final String END = "end";

    public static void main(String[] args) {
        final var graph = InputReader.readAsGraphString("/Day12/input.txt");

        System.out.println("Part 1: " + part1(graph));
        System.out.println("Part 2: " + part2(graph));
    }

    public static long part1(Graph<String> graph) {
        final var allPaths = graph.findAllPaths(START, END, (node, currentPath) -> StringUtils.isAllLowerCase(node));
        return allPaths.size();
    }

    public static long part2(Graph<String> graph) {
        final var allPaths = findAllPaths(graph, START, END);
        return allPaths.size();
    }

    public static Set<List<String>> findAllPaths(Graph<String> graph, String start, String end) {
        Set<List<String>> allRoutes = new HashSet<>();

        allRoutes.add(List.of(start));

        for (var routeCount = allRoutes.size(); ; routeCount = allRoutes.size()) {
            allRoutes = allRoutes.stream().flatMap(currentPath -> nextConnections(graph, currentPath, end)).collect(Collectors.toSet());
            if (allRoutes.size() == routeCount) {
                break;
            }
        }
        return allRoutes;
    }

    static boolean isAllowedPath(List<String> connection) {
        var countPerChar = connection
            .stream()
            .filter(StringUtils::isAllLowerCase)
            .collect(groupingBy(identity(), counting()));

        return countPerChar.values().stream().filter(l -> l > 1).count() <= 1 && countPerChar.values().stream().noneMatch(l -> l > 2);
    }

    private static boolean isAllowed(List<String> connection, String next) {
        if (next.equals(START)) {
            return false;
        }
        if (StringUtils.isAllLowerCase(next)) {
            return isAllowedPath(addImmutableList(connection, next));
        }
        return true;
    }

    private static String last(List<String> path) {
        return path.get(path.size() - 1);
    }

    private static Stream<List<String>> nextConnections(Graph<String> graph, List<String> path, String end) {
        var last = last(path);
        if (last.equals(end)) {
            return Stream.of(path);
        }
        return graph.getNodes().get(last).stream().filter(next -> isAllowed(path, next)).map(next -> addImmutableList(path, next));
    }

    public static class CaveNavigator {

        private static final String DELIMITER = "-";
        private final List<Set<String>> connections;
        private final boolean part2;

        public CaveNavigator(List<String> inputs, boolean part2) {
            this.connections = inputs.stream()
                .map(line -> Stream.of(line.split(DELIMITER)).collect(toSet()))
                .collect(Collectors.toList());
            this.part2 = part2;
        }

        public List<String> calculatePaths() {
            List<String> allRoutes = new LinkedList<>();

            allRoutes.add("start");

            for (var routeCount = allRoutes.size(); ; routeCount = allRoutes.size()) {
                allRoutes = allRoutes.stream().flatMap(this::nextConnections).distinct().collect(Collectors.toList());
                if (allRoutes.size() == routeCount) {
                    break;
                }
            }
            return allRoutes;
        }

        private boolean isAllowed(String connection, String next) {
            if (next.equals("start")) {
                return false;
            }
            if (next.toLowerCase().equals(next)) {
                if (part2) {
                    return isAllowedPath(connection + DELIMITER + next);
                } else {
                    return !connection.contains(next);
                }
            }
            return true;
        }

        static boolean isAllowedPath(String connection) {
            var countPerChar = Arrays.stream(connection.replace("start-", "").replace("-end", "").split(DELIMITER))
                .filter(c -> c.equals(c.toLowerCase()))
                .collect(groupingBy(identity(), counting()));

            return countPerChar.values().stream().filter(l -> l > 1).count() <= 1 && countPerChar.values().stream().noneMatch(l -> l > 2);
        }

        private String last(String connection) {
            return connection.substring(Math.max(0, connection.lastIndexOf('-') + 1));
        }

        Stream<String> next(String start) {
            return connections.stream()
                .filter(connection -> connection.contains(start))
                .flatMap(Collection::stream)
                .filter(item -> !item.equals(start));
        }

        private Stream<String> nextConnections(String connection) {
            var last = last(connection);
            if (last.equals("end")) {
                return Stream.of(connection);
            }
            return next(last).filter(next -> isAllowed(connection, next)).map(next -> connection + DELIMITER + next);
        }


    }
}
