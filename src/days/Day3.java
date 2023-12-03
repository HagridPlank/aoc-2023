package days;

import java.util.ArrayList;
import java.util.List;

public class Day3 extends Day {
    private final List<String> lines = getFileLines();
    private final ArrayList<ArrayList<Integer>> numsInfo = new ArrayList<>();
    private final ArrayList<ArrayList<Integer>> symbolsInfo = new ArrayList<>();

    public Day3(String filePath) {
        super(filePath);
        findNumsAndSymbols();

    }

    @Override
    public int doPart1() {
        int sum = 0;

        for (ArrayList<Integer> num : numsInfo) {
            for (ArrayList<Integer> sym : symbolsInfo) {
                if (isAdjacent(num, sym)) {
                    sum += num.get(0);
                    break;
                }
            }
        }

        return sum;
    }

    @Override
    public int doPart2() {
        int sum = 0;

        for (ArrayList<Integer> sym : symbolsInfo) {
            if (sym.get(2) == '*') {
                int adjCount = 0;
                ArrayList<Integer> gearNums = new ArrayList<>();

                for (ArrayList<Integer> num : numsInfo) {
                    if (isAdjacent(num, sym)) {
                        adjCount++;
                        gearNums.add(num.get(0));

                        if (adjCount > 1) {
                            sum += gearNums.get(0) * gearNums.get(1);
                        }
                    }
                }
            }
        }

        return sum;
    }

    private boolean isSymbol(char ch) {
        return !Character.isLetter(ch) && !Character.isDigit(ch);
    }

    private boolean isAdjacent(ArrayList<Integer> num, ArrayList<Integer> sym) {
        int numRow = num.get(1);
        int numStart = num.get(2);
        int numEnd = numStart + num.get(0).toString().length() - 1;
        int symRow = sym.get(0);
        int symCol = sym.get(1);

        if (((numStart == symCol + 1) || (numEnd == symCol - 1)) && (symRow == numRow || symRow == numRow + 1 || symRow == numRow - 1)) {
            return true;
        }

        for (int i = numStart; i <= numEnd; i++) {
            if (symCol == i && (symRow == numRow + 1 || symRow == numRow - 1)) {
                return true;
            }
        }
        return false;
    }

    private void findNumsAndSymbols() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            StringBuilder currentNum = new StringBuilder();

            for (int j = 0; j < line.length(); j++) {
                char ch = line.charAt(j);

                if (Character.isDigit(ch)) {
                    currentNum.append(ch);

                }
                if ((isSymbol(ch) && !currentNum.isEmpty()) || (j == line.length() - 1 && !currentNum.isEmpty())) {
                    String currentNumStr = currentNum.toString();
                    numsInfo.add(new ArrayList<>(List.of(Integer.parseInt(currentNumStr), i, j - currentNumStr.length())));

                    currentNum = new StringBuilder();
                }

                if (isSymbol(ch) && ch != '.') {
                    symbolsInfo.add(new ArrayList<>(List.of(i, j, (int) ch)));
                }
            }
        }
    }
}
