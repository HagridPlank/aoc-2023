package days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Day {
    private List<String> fileLines;

    public Day(String filePath) {
        try {
            Path path = Paths.get(filePath);
            fileLines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public abstract int doPart1();
    public abstract int doPart2();
}
