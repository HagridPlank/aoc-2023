package days;

import java.util.*;

public class Day1 extends Day {
    private final List<String> lines = getFileLines();

    public Day1(String filePath) {
        super(filePath);
    }

    @Override
    public long doPart1() {
        int total = 0;

        for (String line : lines) {
            List<Integer> nums = new ArrayList<>();

            for (char currentChar : line.toCharArray()) {
                if (currentChar > 47 && currentChar < 58) {
                    nums.add(currentChar - 48);
                }
            }

            total += calcSumFirstLast(nums);
        }

        return total;
    }

    @Override
    public long doPart2() {
        Map<String, Integer> spelledNums = craftSpelledNums();
        int total = 0;

        for (String line : lines) {
            List<Integer> nums = new ArrayList<>();

            for (int i = 0; i < line.length(); i++) {
                String str = line.substring(i);
                char chr = line.charAt(i);

                if (chr > 47 && chr < 58) {
                    nums.add(chr - 48);
                } else {
                    for (Map.Entry<String, Integer> spelledNum : spelledNums.entrySet()) {
                        if (str.startsWith(spelledNum.getKey())) {
                            nums.add(spelledNum.getValue());
                            i += spelledNum.getKey().length() - 2;
                        }
                    }
                }
            }

            total += calcSumFirstLast(nums);
        }
        return total;
    }

    private int calcSumFirstLast(List<Integer> nums) {
        if (nums.size() == 1) {
            int val = nums.get(0);
            return val * 10 + val;
        } else {
            return nums.get(0) * 10 + nums.get(nums.size() - 1);
        }
    }

    private HashMap<String, Integer> craftSpelledNums() {
        List<String> spelledNumsArr = new ArrayList<>(Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine"));
        HashMap<String, Integer> spelledNums = new HashMap<>();

        for (int i = 0; i < spelledNumsArr.size(); i++) {
            spelledNums.put(spelledNumsArr.get(i), i + 1);
        }

        return spelledNums;
    }
}
