/**
 * Copyright © 2014, Oracle and/or its affiliates. All rights reserved.
 * <p>
 * JDK 8 MOOC Lesson 3 homework
 */
package lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Class to generate a list of random words
 *
 * @author Simon Ritter (@speakjava)
 */
public class RandomWords {
    private final List<String> sourceWords;

    /**
     * Constructor
     *
     * @throws IOException If the source words file cannot be read
     */
    public RandomWords() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(getPath("words.txt"))) {
            sourceWords = reader.lines().collect(Collectors.toList());
            System.out.println("Loaded " + sourceWords.size() + " words");
        }
    }

    /**
     * Create a list of a given size containing random words
     *
     * @param listSize The size of the list to create
     * @return The created list
     */
    public List<String> createList(int listSize) {
        List<String> wordList = new Random()
            .ints(listSize, 0, listSize)
            .mapToObj(sourceWords::get)
            .collect(Collectors.toList());
        return wordList;
    }

    /**
     * Return the list of all source words, which cannot be modified
     *
     * @return The unmodifiable list of all source words
     */
    public List<String> allWords() {
        return Collections.unmodifiableList(sourceWords);
    }

    private static Path getPath(String filename) {
        Path path;
        try {
            path = Paths.get(RandomWords.class.getResource(filename).toURI()).toAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unable to locate file", e);
        }
        return path;
    }

    public static void main(String[] args) throws IOException {
        RandomWords randomWords = new RandomWords();
        List<String> list = randomWords.createList(100);
        list.forEach(System.out::println);
    }
}