package com.manye.aoc2021.model;

import lombok.Value;

@Value
public class Command {

    public enum Type {
        forward,
        down,
        up;
    }

    Type type;
    long number;

}
