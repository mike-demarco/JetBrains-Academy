package problemoftheday;

import java.util.Scanner;

class TheSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int input = scanner.nextInt();
        int count = 0;

        for (int i = 1; i < input + 1; i++) {
            if (count >= input) {
                break;
            }
            for (int j = 0; j < i; j++) {
                System.out.print(i + " ");
                count++;
                if (count >= input) {
                    break;
                }
            }
        }
    }
}