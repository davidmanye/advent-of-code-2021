package com.manye.aoc2021.model;

import com.google.common.base.MoreObjects;

import java.util.List;

public class Submarine {

    private long horizontal;
    private long depth;
    private long aim;

    public Submarine() {
        this(0, 0, 0);
    }

    public Submarine(long horizontal, long depth, long aim) {
        this.horizontal = horizontal;
        this.depth = depth;
        this.aim = aim;
    }

    public long getHorizontal() {
        return horizontal;
    }

    public long getDepth() {
        return depth;
    }

    public void execute(List<Command> commands) {
        for (Command command: commands) {
            switch (command.getType()) {
                case up:
                    executeUp(command.getNumber());
                    break;
                case down:
                    executeDown(command.getNumber());
                    break;
                case forward:
                    executeForward(command.getNumber());
                    break;
            }
        }
    }

    private void executeForward(long number) {
        horizontal += number;
        depth += aim * number;
    }

    private void executeDown(long number) {
        aim += number;
    }

    private void executeUp(long number) {
        aim -= number;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("horizontal", horizontal)
            .add("depth", depth)
            .add("aim", aim)
            .toString();
    }
}
