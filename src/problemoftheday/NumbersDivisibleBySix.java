package problemoftheday;

import java.util.Scanner;

class NumbersDivisibleBySix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here

        int length = scanner.nextInt();
        int sum = 0;

        for (int i = 0; i < length; i++) {
            int number = scanner.nextInt();
            if (number % 6 == 0) {
                sum += number;
            }
        }
        System.out.print(sum);
    }
}