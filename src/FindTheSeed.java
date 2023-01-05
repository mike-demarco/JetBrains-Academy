import java.util.*;

public class FindTheSeed {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here

        // input values
        int lowerA = scanner.nextInt();
        int upperB = scanner.nextInt();
        int countN = scanner.nextInt();
        int rangeK = scanner.nextInt();

        // init lists
        List<Integer> tempList = new ArrayList<>();
        int minValue = 0;
        int lowestSeed = 0;

        // generate random strings
        for (int i = lowerA; i < upperB + 1; i++) {
            int maxValue = 0;
            Random seed = new Random(i);
            for (int j = 0; j < countN; j++) {
                tempList.add(seed.nextInt(rangeK));
            }
            //retrieve max value
            maxValue = tempList.stream().max(Integer::compareTo).get();
            // check values
            if (i == lowerA || maxValue < minValue) {
                minValue = maxValue;
                lowestSeed = i;
            }
            // reset tempList
            tempList.clear();
        }

        System.out.println(lowestSeed);
        System.out.print(minValue);
    }
}