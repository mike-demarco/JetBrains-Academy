package amazingnumbers;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AmazingNumbers {

    public static void welcomeUsers() {
        System.out.println("""
            Welcome to Amazing Numbers!
                            
            Supported requests:
            - enter a natural number to know its properties;
            - enter two natural numbers to obtain the properties of the list:
              * the first parameter represents a starting number;
              * the second parameter shows how many consecutive numbers are to be processed;
            - separate the parameters with one space;
            - enter 0 to exit.""");
    }

    public static String[] askForRequest(Scanner scanner) {
        System.out.println("Enter a request:");
        String[] input = scanner.nextLine().split(" ");
        int value1 = Integer.parseInt(input[0]);
        if (input.length > 1) {
            int value2 = Integer.parseInt(input[1]);
        }
        return input;
    }

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

    public static boolean gap(long number, int numberLength) {
        boolean isGapful = false;
        if (numberLength < 3) {
            return isGapful;
        }

        char firstDigit = (char) (number / Math.pow(10, numberLength) % 10);
        char lastDigit = (char) (number / 10 % 10);
        String gapNumber = "" + firstDigit + lastDigit;
        int gapNumberConcatenated = (int) gapNumber.charAt(0) * 10 + (int) gapNumber.charAt(1);
        isGapful = gapNumberConcatenated % 12 == 0;

        return isGapful;
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
            List<String> propertiesList = new LinkedList<>();
            propertiesList.add(number + " is ");
            boolean isEven = parity(number)[0];
            if (isEven) propertiesList.add("even");
            boolean isOdd = parity(number)[1];
            if (isEven) propertiesList.add("odd");
            boolean isBuzz = buzz(number);
            if (isEven) propertiesList.add(", buzz");
            boolean isDuck = duck(number, inputNumLength);
            if (isEven) propertiesList.add(", duck");
            boolean isPalindromic = palindrome(number, inputNumLength);
            if (isEven) propertiesList.add(", palindromic");
            System.out.println(propertiesList);
            System.out.print("Enter a request:");
            number = scanner.nextLong();
        }
    }

    public static void gameLoop(long number, long count, Scanner scanner) {
        for (int i = 0; i < count; i++) {
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
                if (isEven, )
                System.out.printf("%d is %b, %b, %b", number, isEven, isOdd, isBuzz, isDuck, isPalindromic);
                System.out.print("Enter a request:");
                number = scanner.nextLong();
            }
            number++;
        }
    }

    public static void main(String[] args) {
        boolean gameExit = false;
        welcomeUsers();
        Scanner scanner = new Scanner(System.in);
        String[] input = askForRequest(scanner);
        if (value1 == 0) gameExit = true;
        else if (value2sEmpty()) System.out.println("The first parameter should be a natural number or zero.");
        else if (input[0]. < 0)
        If numbers are not natural, print an error message;
        If one number is entered, calculate and print the properties of this number;
        For two numbers, print the list of numbers with properties;
        Once the request is processed, continue execution from step 3.



        while (!gameExit) {
            long naturalNumberInput = Integer.parseInt(input[0]);
            if (input.length > 1) {
                long naturalNumberCount = Integer.parseInt(input[1]);
                gameLoop(naturalNumberInput, naturalNumberCount, scanner);
            } else gameLoop(naturalNumberInput, scanner);
            input = scanner.nextLine().split(" ");
            if (input[0].equals("0")) gameExit = true;
        }

        System.out.print("Goodbye!");
    }
}