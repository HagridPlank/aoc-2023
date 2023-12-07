package days;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

public class Day7 extends Day {
    private final List<String> lines = getFileLines();
    private final List<SimpleEntry<String, Integer>> hands = new ArrayList<>();
    private final List<Character> sortedCards = new ArrayList<>(Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'));

    public Day7(String filePath) {
        super(filePath);
        parseHands();
    }

    @Override
    public long doPart1() {
        for (int i = 1; i < hands.size(); i++) {
            for (int j = 1; j < hands.size(); j++) {
                if (isStronger(hands.get(j-1).getKey(), hands.get(j).getKey())) {
                    SimpleEntry<String, Integer> temp = hands.get(j);
                    hands.set(j, hands.get(j-1));
                    hands.set(j-1, temp);
                }
            }
        }
        int total = 0;

        for (int i = 0; i < hands.size(); i++) {
            total += hands.get(i).getValue() * (i+1);
        }

        return total;
    }

    @Override
    public long doPart2() {
        return 0;
    }

    private void parseHands() {
        for (String line : lines) {
            String[] hand = line.split(" ");
            hands.add(new SimpleEntry<>(hand[0], Integer.parseInt(hand[1])));
        }
    }

    private boolean isStronger(String hand1, String hand2) {
        HashMap<Character, Integer> cards1 = makeCardCountMapping(hand1);
        HashMap<Character, Integer> cards2 = makeCardCountMapping(hand2);

        if (calcStrength(cards1) < calcStrength(cards2)) {
            return true;
        } else if (calcStrength(cards1) > calcStrength(cards2)) {
            return false;
        } else {
            char[] hand1Arr = hand1.toCharArray();
            char[] hand2Arr = hand2.toCharArray();

            for (int i = 0; i < hand1Arr.length; i++) {
                if (isGreater(hand1Arr[i], hand2Arr[i])) {
                    return true;
                } else if (isGreater(hand2Arr[i], hand1Arr[i])) {
                    return false;
                }
            }

            return false;
        }
    }

    private boolean isGreater(char a, char b) {
        return sortedCards.indexOf(a) < sortedCards.indexOf(b);
    }

    private HashMap<Character, Integer> makeCardCountMapping(String hand) {
        HashMap<Character, Integer> cards = new HashMap<>();

        for (char ch : hand.toCharArray()) {
            if (cards.containsKey(ch)) {
                int val = cards.get(ch);
                val++;
                cards.put(ch, val);
            } else {
                cards.put(ch, 1);
            }
        }

        return cards;
    }

    /*
    1 = 5 of a kind
    2 = 4 of a kind
    3 = Full house
    4 = Three of a kind
    5 = Two pair
    6 = One pair
    7 = High card
     */
    private int calcStrength(HashMap<Character, Integer> cards) {
        if (cards.containsValue(5)) {
            return 1;
        } else if (cards.containsValue(4)) {
            return 2;
        } else if (cards.containsValue(3)) {
            if (cards.containsValue(2)) {
                return 3;
            } else {
                return 4;
            }
        } else if (cards.containsValue(2)) {
            int pairCount = 0;
            for (int val : cards.values()) {
                if (val == 2) {
                    pairCount++;
                }
            }
            if (pairCount > 1) {
                return 5;
            } else {
                return 6;
            }
        } else {
            return 7;
        }
    }
}
