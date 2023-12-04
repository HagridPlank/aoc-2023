package days;

import java.util.*;

public class Day4 extends Day {
    private final List<String> fileLines = getFileLines();
    private HashMap<Integer, Integer> cardToWins;
    private int cardCount;

    public Day4(String filePath) {
        super(filePath);
        cardCount = 0;
        craftCardToWins();
    }

    @Override
    public int doPart1() {
        int points = 0;

        for (String line : fileLines) {
            String[] parsedLine = line.split(":")[1].split("\\|");
            String[] winningNums = parsedLine[0].trim().split(" \s*");
            String[] myNums = parsedLine[1].trim().split(" \s*");

            int matched = 0;

            for (String winningNum : winningNums) {
                for (String myNum : myNums) {
                    if (winningNum.equals(myNum)) {
                        matched++;
                    }
                }
            }

            points += (matched == 0) ? 0 : Math.pow(2, matched - 1);
        }

        return points;
    }

    @Override
    public int doPart2() {
        System.out.println(cardToWins);
        for (int i = 1; i <= fileLines.size(); i++) {
            calcCard(i);
        }
        return cardCount;
    }

    private void calcCard(int card) {
        cardCount++;
        for (int i = 1; i <= cardToWins.get(card); i++) {
            System.out.printf("Card: %d    Called: %d\n", card, i + card);
            calcCard(i + card);
        }
    }

    private void craftCardToWins() {
        cardToWins = new HashMap<>();

        int points = 0;

        for (String line : fileLines) {
            String[] parsedLine = line.split(":");
            int card = Integer.parseInt(parsedLine[0].replaceAll("[^0-9]", ""));

            String[] parsedLineNums = parsedLine[1].split("\\|");
            String[] winningNums = parsedLineNums[0].trim().split(" \s*");
            String[] myNums = parsedLineNums[1].trim().split(" \s*");

            int matched = 0;

            for (String winningNum : winningNums) {
                for (String myNum : myNums) {
                    if (winningNum.equals(myNum)) {
                        matched++;
                    }
                }
            }

            cardToWins.put(card, matched);
        }
    }
}