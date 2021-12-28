package com.manye.aoc2021.model.core;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toMap;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

public class MatrixDijkstraState<T> {

    private final Matrix<T> matrix;
    private final Map<Coordinate, Node<T>> table;
    private final PriorityQueue<Node<T>> unvisited;
    private final HashSet<Coordinate> visited;

    public MatrixDijkstraState(Matrix<T> matrix) {
        this.matrix = matrix;
        this.table = matrix.stream()
            .collect(
                toMap(
                    Matrix.Entry::getCoordinate,
                    entry -> new Node<>(entry.getCoordinate())));
        this.unvisited = new PriorityQueue<>(comparingLong(Node::getTotalCost));
        this.visited = new HashSet<>();

    }

    public void calculateCosts(Coordinate start, Function<Matrix<T>.Entry, Long> costFunction, Function<Matrix<T>.Entry, List<Matrix<T>.Entry>> getAdjacent) {
        this.table.get(start).setTotalCost(0);
        this.unvisited.add(table.get(start));
        while (!unvisited.isEmpty()) {
            final var current = unvisited.poll().getCoordinate();
            visited.add(current);
            getAdjacent.apply(matrix.getEntry(current))
                .stream()
                .filter(entry -> !visited.contains(entry.getCoordinate()))
                .forEach(adjacent -> {
                    final var totalCosts = costFunction.apply(adjacent) + table.get(current).getTotalCost();
                    final var adjacentNode = table.get(adjacent.getCoordinate());
                    if (totalCosts < adjacentNode.getTotalCost()) {
                        adjacentNode.setPrevious(current);
                        adjacentNode.setTotalCost(totalCosts);
                        unvisited.remove(adjacentNode);
                        unvisited.add(adjacentNode);
                    }
                });
        }
    }

    public LinkedList<Matrix<T>.Entry> bestRoute(Coordinate end) {
        var route = new LinkedList<Matrix<T>.Entry>();
        var routeItem = table.get(end);
        while (routeItem.getPrevious() != null) {
            route.addFirst(matrix.getEntry(routeItem.getCoordinate()));
            routeItem = table.get(routeItem.getPrevious());
        }
        route.addFirst(matrix.getEntry(routeItem.getCoordinate()));
        return route;
    }

    public long getTotalCost(Coordinate coordinate) {
        return table.get(coordinate).getTotalCost();
    }


    @Getter
    @Setter
    public static class Node<T> {
        private final Coordinate coordinate;
        private long totalCost = Long.MAX_VALUE;
        private Coordinate previous;

        public Node(Coordinate coordinate) {
            this.coordinate = coordinate;
        }
    }
}
