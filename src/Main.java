import days.Day;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    void downloadTxtFile() {
        String filepath = "dayFiles/day1.txt";

    }
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        do {
            System.out.print("Choose day: ");
            int day = s.nextInt();
            if (day == 0) break;

            String path = String.format("src/dayFiles/day%s.txt", day);
            String className = String.format("days.Day%s", day);

            try {
                // Get the class
                Class<?> c = Class.forName(className);
                // Create an instance of the class
                Object o = c.getConstructor(String.class).newInstance(path);
                // Cast the object to a Day
                Day d = (Day) o;
                System.out.printf("Part 1: %d\nPart 2: %d%n", d.doPart1(), d.doPart2());

            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException |
                     InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } while(true);
    }
}
