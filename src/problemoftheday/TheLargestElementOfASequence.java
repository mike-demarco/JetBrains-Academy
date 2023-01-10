package problemoftheday;

import java.util.Scanner;

class TheLargestElementOfASequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int max = 0;
        int currentNumber = 0;
        do {
            currentNumber = scanner.nextInt();
            if (currentNumber > max) {
                max = currentNumber;
            }
        } while (currentNumber != 0);
        System.out.print(max);
    }
}
