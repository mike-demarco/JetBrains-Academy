import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BullsAndCows3 {
    public static String randomGenerator(int length) {
        List<Integer> randomList = new ArrayList<>(List.of(0, 2, 3, 4, 5, 6, 7, 8, 9));
        Collections.shuffle(randomList);
        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }
        return result.toString();
    }
}
