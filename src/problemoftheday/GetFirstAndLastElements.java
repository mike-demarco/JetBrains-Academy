package problemoftheday;

import java.util.*;

public class GetFirstAndLastElements {

    // write a method here
    public static int[] getFirstAndLast(int[] inputAr) {
        return new int[] {inputAr[0], inputAr[inputAr.length - 1]};
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] result = getFirstAndLast(array);
        Arrays.stream(result).forEach(e -> System.out.print(e + " "));
    }
}
