package com.manye.aoc2021.model.polymer;

import lombok.Value;

@Value
public class InsertionRule {

    String pairInsertion;
    String elementReplace;
    String replacement;

    public InsertionRule(String pairInsertion, String elementReplace) {
        this.pairInsertion = pairInsertion;
        this.elementReplace = elementReplace;
        this.replacement = buildReplacement(pairInsertion, elementReplace);
    }

    private static String buildReplacement(String pairInsertion, String elementReplace) {
        final var split = pairInsertion.split("");
        return split[0] + elementReplace + split[1];
    }

}
