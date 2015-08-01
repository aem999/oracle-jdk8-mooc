import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

/**
 * Find the longest line in a file using different approaches
 */
public class LongestLine {

    public static void main(String[] args) throws IOException {
        Path path = getPath("LongestLine.txt");

        String s1 = findLongestLineIterative(path);
        System.out.println("The longest line (iterative) is: " + s1);

        String s2 = findLongestLineRecursive("", 0, Files.readAllLines(path));
        System.out.println("The longest line (recursive) is: " + s2);

        String s3 = findLongestLineStream(path);
        System.out.println("The longest line (stream)    is: " + s3);

        String s4 = findLongestLineStream2(path);
        System.out.println("The longest line (stream2)   is: " + s4);
    }

    private static String findLongestLineIterative(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String longestLine = "";
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > longestLine.length()) {
                    longestLine = line;
                }
            }
            return longestLine;
        }
    }

    private static String findLongestLineRecursive(String longestLine, int index, List<String> lines) {
        if (index >= lines.size()) {
            return longestLine;
        }

        if (index == lines.size() - 1) {
            if (longestLine.length() > lines.get(index).length()) {
                return longestLine;
            } else {
                return lines.get(index);
            }
        }

        String s = findLongestLineRecursive(lines.get(index), index + 1, lines);
        return longestLine.length() > s.length() ? longestLine : s;
    }

    private static String findLongestLineStream(Path path) throws IOException {
        return Files.lines(path)
            .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2)
            .get();
    }

    private static String findLongestLineStream2(Path path) throws IOException {
        return Files.lines(path).max(Comparator.comparingInt(String::length)).get();
    }

    private static Path getPath(String filename) {
        Path path;
        try {
            path = Paths.get(LongestLine.class.getResource(filename).toURI()).toAbsolutePath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Unable to locate file", e);
        }
        return path;
    }
}
