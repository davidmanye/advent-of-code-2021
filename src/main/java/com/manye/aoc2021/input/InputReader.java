package com.manye.aoc2021.input;

import com.manye.aoc2021.utils.StreamUtils;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class InputReader {

    public static List<Integer> readAsInt(String resourcePath) {
        return readLines(resourcePath)
            .map(Integer::parseInt)
            .collect(Collectors.toList());
    }

    private static Stream<String> readLines(String resourcePath) {
        return StreamUtils.fromIterator(IOUtils.lineIterator(getInputStream(resourcePath), StandardCharsets.UTF_8));
    }

    public static InputStream getInputStream(String resourcePath) {
        return InputReader.class.getResourceAsStream(resourcePath);
    }

}
