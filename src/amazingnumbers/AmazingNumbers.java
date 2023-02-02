package amazingnumbers;

import java.util.Scanner;

public class AmazingNumbers {

    public static boolean[] parity(long number) {
        boolean isEven = number % 2 == 0;
        boolean isOdd = number % 2 == 1;
        boolean[] parity = new boolean[] {isEven, isOdd};
        return parity;
    }

    public static boolean buzz(long number) {
        boolean divisibleBy7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;
        boolean isBuzz = divisibleBy7 || endsWith7;
        return isBuzz;
    }

    public static boolean duck(long number, int length) {
        boolean isDuck = false;
        for (long i = 1; i < Math.pow(10, length); i *= 10) {
            int index = (int) (number / i % 10);
            boolean check = index == 0;
            if (check) {
                isDuck = true;
                break;
            }
        }
        return isDuck;
    }

    public static boolean palindrome(long number, int numberLength) {
        boolean isPalindromic = false;
        int tempDigitLeft, tempDigitRight, iLength;
        int halfNumberLength = numberLength / 2 + 1;
        int counter = 1;
        for (long i = 1; i < Math.pow(10, halfNumberLength); i *= 10) {
            // escape check
            iLength = String.valueOf(i).length();
            if (iLength == halfNumberLength) {
                isPalindromic = true;
                break;
            };

            // check
            tempDigitLeft = (int) (number / Math.pow(10, numberLength - counter) % 10);
            tempDigitRight = (int) (number / (double) i % 10);
            if (tempDigitLeft != tempDigitRight) break;
            else counter++;
        }
        return isPalindromic;
    }

    public static void gameLoop(long number, Scanner scanner) {
        while (number != 0) {
            if (number < 0) {
                System.out.println("The first parameter should be a natural number or zero.");
                System.out.print("Enter a request:");
                number = scanner.nextLong();
                continue;
            }
            int inputNumLength = String.valueOf(number).length();
            boolean isEven = parity(number)[0];
            boolean isOdd = parity(number)[1];
            boolean isBuzz = buzz(number);
            boolean isDuck = duck(number, inputNumLength);
            boolean isPalindromic = palindrome(number, inputNumLength);
            System.out.printf(
                    """
                            Properties of %d
                                even: %b
                                odd: %b
                                buzz: %b
                                duck: %b
                                palindromic: %b
                            """, number, isEven, isOdd, isBuzz, isDuck, isPalindromic
            );
            System.out.print("Enter a request:");
            number = scanner.nextLong();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
            Welcome to Amazing Numbers!
            
            Supported requests:
                - enter a natural number to know its properties;
                - enter 0 to exit.
                
            Enter a request:""");
        long naturalNumberInput = scanner.nextLong();
        gameLoop(naturalNumberInput, scanner);
        System.out.print("Goodbye!");
    }
}