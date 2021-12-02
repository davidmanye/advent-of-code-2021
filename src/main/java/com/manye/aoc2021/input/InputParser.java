package com.manye.aoc2021.input;

import com.manye.aoc2021.model.Command;

public final class InputParser {

    public static Command parseCommand(String s) {
        var split = s.split(" ");
        return new Command(Command.Type.valueOf(split[0]), Integer.parseInt(split[1]));
    }
}
