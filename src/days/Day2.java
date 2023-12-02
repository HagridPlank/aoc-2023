package days;

import java.util.List;

public class Day2 extends Day {
    private final List<String> lines = getFileLines();

    public Day2(String filePath) {
        super(filePath);
    }

    @Override
    public int doPart1() {
        int sum = 0;

        for (String line : lines) {
            boolean tooMany = false;

            String[] parsedLine = line.split("[:;]");
            int id = Integer.parseInt(parsedLine[0].substring(5));

            for (int i = 1; i < parsedLine.length; i++) {
                String[] cubeArr = parsedLine[i].substring(1).split(",\\s*");
                for (String cube : cubeArr) {
                    String[] parsedCube = cube.split(" ");
                    switch (parsedCube[1]) {
                        case "red" -> {
                            if (Integer.parseInt(parsedCube[0]) > 12) { tooMany = true; }
                        }
                        case "green" -> {
                            if (Integer.parseInt(parsedCube[0]) > 13) { tooMany = true; }
                        }
                        case "blue" -> {
                            if (Integer.parseInt(parsedCube[0]) > 14) { tooMany = true; }
                        }
                    }
                }
            }
            if (!tooMany) {
                sum += id;
            }
        }
        return sum;
    }

    @Override
    public int doPart2() {
        int sum = 0;

        for (String line : lines) {
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;

            String[] parsedLine = line.split("[:;]");

            for (int i = 1; i < parsedLine.length; i++) {
                String[] cubeArr = parsedLine[i].substring(1).split(",\\s*");

                for (String cube : cubeArr) {
                    String[] parsedCube = cube.split(" ");
                    int num = Integer.parseInt(parsedCube[0]);

                    switch (parsedCube[1]) {
                        case "red" -> {
                            if (num > maxRed) { maxRed = num; }
                        }
                        case "green" -> {
                            if (num > maxGreen) { maxGreen = num; }
                        }
                        case "blue" -> {
                            if (num > maxBlue) { maxBlue = num; }
                        }
                    }
                }
            }
            sum += maxRed * maxGreen * maxBlue;
        }
        return sum;
    }
}
