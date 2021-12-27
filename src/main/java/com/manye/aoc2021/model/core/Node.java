package com.manye.aoc2021.model.core;

import lombok.Value;

import java.util.List;

@Value
public class Node<T> {

    T value;
    List<Node<T>> nodes;

}
