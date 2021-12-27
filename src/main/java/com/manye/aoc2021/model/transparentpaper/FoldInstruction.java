package com.manye.aoc2021.model.transparentpaper;

import lombok.Value;

@Value
public class FoldInstruction {

    public enum Type {
        X,
        Y;
    }

    Type type;
    int value;
}
