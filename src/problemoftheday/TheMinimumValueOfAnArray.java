package problemoftheday;

import java.util.Scanner;
import java.util.stream.IntStream;

class TheMinimumValueOfAnArray {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[scanner.nextInt()];

        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
        }

        int minimum = IntStream.of(input).min().orElse(Integer.MAX_VALUE);
        System.out.print(minimum);
    }
}