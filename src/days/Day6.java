package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 extends Day {
    private final List<String> lines = getFileLines();
    private final List<int[]> races = new ArrayList<>();


    public Day6(String filePath) {
        super(filePath);
        parseRaces();
    }

    @Override
    public long doPart1() {
        int multWays = 1;
        for (int[] race : races) {
            int ways = 0;
            int time = race[0], dist = race[1];

            for (int waitTime = 1; waitTime < time; waitTime++) {
                int myDistance = waitTime * (time - waitTime);
                if (myDistance > dist) {
                    ways++;
                }
            }
            multWays *= ways;
        }
        return multWays;
    }

    @Override
    public long doPart2() {
        long time = Long.parseLong(lines.get(0).split(":")[1].replaceAll(" ", ""));
        long dist = Long.parseLong(lines.get(1).split(":")[1].replaceAll(" ", ""));

        int ways = 0;

        for (long waitTime = 1; waitTime < time; waitTime++) {
            long myDistance = waitTime * (time - waitTime);
            if (myDistance > dist) {
                ways++;
            }
        }
        return ways;
    }

    private void parseRaces() {
        List<Integer> times = Arrays.stream(lines.get(0).split(":")[1].trim().split(" \s*\s*")).map(Integer::parseInt).toList();
        List<Integer> distances = Arrays.stream(lines.get(1).split(":")[1].trim().split(" \s*\s*")).map(Integer::parseInt).toList();

        for (int i = 0; i < times.size(); i++) {
            races.add(new int[]{times.get(i), distances.get(i)});
        }
    }
}
