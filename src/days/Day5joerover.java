package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day5joerover extends Day {
    private final List<String> lines = getFileLines();

    private ArrayList<Long> seeds = new ArrayList<>();
    private HashMap<Long, Long> map1 = new HashMap<>();
    private HashMap<Long, Long> map2 = new HashMap<>();
    private HashMap<Long, Long> map3 = new HashMap<>();
    private HashMap<Long, Long> map4 = new HashMap<>();
    private HashMap<Long, Long> map5 = new HashMap<>();
    private HashMap<Long, Long> map6 = new HashMap<>();
    private HashMap<Long, Long> map7 = new HashMap<>();

    public Day5joerover(String filePath) {
        super(filePath);
        craftMaps();
    }

    @Override
    public long doPart1() {
        long lowest = Long.MAX_VALUE;

        for (long seed : seeds) {
            long v1 = map1.getOrDefault(seed, seed);
            long v2 = map2.getOrDefault(v1, v1);
            long v3 = map3.getOrDefault(v2, v2);
            long v4 = map4.getOrDefault(v3, v3);
            long v5 = map5.getOrDefault(v4, v4);
            long v6 = map6.getOrDefault(v5, v5);
            long location = map7.getOrDefault(v6, v6);
            lowest = Math.min(location, lowest);
        }

        return lowest;
    }

    @Override
    public long doPart2() {
        return 0;
    }

    private void craftMaps() {
        int category = 0;

        for (String line : lines) {
            System.out.println(line);

            if (line.isEmpty()) {
                continue;
            }

            if (line.contains("seeds:")) {
                seeds.addAll(Arrays.stream(line.split(":")[1]
                        .trim().split(" ")).map(Long::parseLong).toList());
            } else if (line.contains(":")) {
                category++;
            } else {
                List<Long> parsedLine = Arrays.stream(line.split(" ")).map(Long::parseLong).toList();

                switch (category) {
                    case 1 -> addMapping(map1, parsedLine);
                    case 2 -> addMapping(map2, parsedLine);
                    case 3 -> addMapping(map3, parsedLine);
                    case 4 -> addMapping(map4, parsedLine);
                    case 5 -> addMapping(map5, parsedLine);
                    case 6 -> addMapping(map6, parsedLine);
                    case 7 -> addMapping(map7, parsedLine);
                }
            }
        }
    }

    private void addMapping(HashMap<Long, Long> map, List<Long> parsedLine) {
        for (int i = 0; i < parsedLine.get(2); i++) {
            map.put(parsedLine.get(1) + i, parsedLine.get(0) + i);
        }
    }
}
