package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 extends Day {
    private final List<String> fileLines = getFileLines();
    private final List<Long> seeds = new ArrayList<>();

    public Day5(String filePath) {
        super(filePath);
        parseSeeds();
        fileLines.subList(0, 2).clear();
    }

    @Override
    public long doPart1() {
        long lowest = Long.MAX_VALUE;

        for (long seed : seeds) {
            boolean found = false;
            long val = seed;

            for (String line : fileLines) {
                if (!line.isEmpty()) {
                    if (line.contains(":")) {
                        found = false;
                    } else if (!found) {
                        List<Long> parsedLine = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
                        long newVal = findMapping(parsedLine.get(0), parsedLine.get(1), parsedLine.get(2), val);
                        found = newVal != val;
                        val = newVal;
                    }
                }
            }

            lowest = Math.min(lowest, val);
        }
        
        return lowest;
    }

    @Override
    public long doPart2() {
        long lowest = Long.MAX_VALUE;

        for (int i = 0; i < seeds.size(); i += 2) {
            for (long j = 0; j < seeds.get(i + 1); j++) {

                long seed = seeds.get(i) + j;

                boolean found = false;
                long val = seed;

                for (String line : fileLines) {
                    if (!line.isEmpty()) {
                        if (line.contains(":")) {
                            found = false;
                        } else if (!found) {
                            List<Long> parsedLine = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();
                            long newVal = findMapping(parsedLine.get(0), parsedLine.get(1), parsedLine.get(2), val);
                            found = newVal != val;
                            val = newVal;
                        }
                    }
                }
                lowest = Math.min(lowest, val);
            }
        }

        return lowest;
    }

    private void parseSeeds() {
        seeds.addAll(Arrays.stream(fileLines.get(0)
                .split(":")[1].trim()
                .split(" ")).map(Long::parseLong).toList());
    }

    private long findMapping(long destination, long source, long range, long val) {
        if (val >= source && val < source + range) {
            return destination + (val - source);
        } else {
            return val;
        }
    }
}
