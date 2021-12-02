package com.manye.aoc2021.days;

import com.manye.aoc2021.input.InputReader;
import com.manye.aoc2021.model.Command;
import com.manye.aoc2021.model.Submarine;

import java.util.List;

public class Day02 {

    public static void main(String[] args) {
        var commands = InputReader.reasAsCommands("/Day02/input.txt");
//        System.out.println("Part 1: " + part1(commands));
        System.out.println("Part 2: " + part2(commands));
    }

    @Deprecated
    public static long part1(List<Command> commands) {
        var submarine = new Submarine();
        submarine.execute(commands);
        return submarine.getHorizontal() * submarine.getDepth();
    }

    public static long part2(List<Command> commands) {
        var submarine = new Submarine();
        submarine.execute(commands);
        return submarine.getHorizontal() * submarine.getDepth();
    }
}
