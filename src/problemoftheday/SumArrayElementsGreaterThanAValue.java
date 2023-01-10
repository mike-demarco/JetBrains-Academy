package problemoftheday;

import java.util.Scanner;

class SumArrayElementsGreaterThanAValue {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[scanner.nextInt()];
        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
        }
        int minimum = scanner.nextInt();
        int summation = 0;
        for (int integer: input) {
            if (integer > minimum) {
                summation += integer;
            } else {
                continue;
            }
        }
        System.out.print(summation);
    }
}