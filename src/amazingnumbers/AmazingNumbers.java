package amazingnumbers;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AmazingNumbers {

    public static void printTheInstructions() {
        System.out.println("""
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
        return input;
    }

    public static boolean[] parity(long number) {
        boolean isEven = number % 2 == 0;
        boolean isOdd = number % 2 == 1;
        boolean[] parity = new boolean[]{isEven, isOdd};
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
        int indexNumLength = 1;
        for (long i = 1; indexNumLength < length; i *= 10) {
            long index = number / i % 10;
            boolean check = index == 0;
            indexNumLength = String.valueOf(i).length();
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
            }
            ;

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

        long firstDigit = (long) Math.floor(number / Math.pow(10, numberLength - 1) % 10);
        long lastDigit = number % 10;
        long gapNumberConcatenated = firstDigit * 10 + lastDigit;
        long gapNumberValue = number % gapNumberConcatenated;
        isGapful = gapNumberValue == 0;

        return isGapful;
    }

    public static void calculatePropertiesOne(long number) {
        int inputNumLength = String.valueOf(number).length();
        List<String> propertiesList = new LinkedList<>();
        boolean isBuzz = buzz(number);
        boolean isDuck = duck(number, inputNumLength);
        boolean isPalindromic = palindrome(number, inputNumLength);
        boolean isGapful = gap(number, inputNumLength);
        boolean isEven = parity(number)[0];
        boolean isOdd = parity(number)[1];
        System.out.printf("""
            Properties of %d
                    buzz: %b
                    duck: %b
             palindromic: %b
                    gapful: %b
                    even: %b
                    odd: %b
           """, number, isBuzz, isDuck, isPalindromic, isGapful, isEven, isOdd);
    }

    public static void calculatePropertiesTwo(long number, long count) {
        int inputNumLength;
        List<String> propertiesList;
        long finalNumberChecked = number + count;
        String strList;

        for (long i = number; i < finalNumberChecked; i++) {
            inputNumLength = String.valueOf(i).length();
            propertiesList = new LinkedList<>();
            propertiesList.add(i + " is ");
            boolean isBuzz = buzz(i);
            if (isBuzz) propertiesList.add("buzz, ");
            boolean isDuck = duck(i, inputNumLength);
            if (isDuck) propertiesList.add("duck, ");
            boolean isPalindromic = palindrome(i, inputNumLength);
            if (isPalindromic) propertiesList.add("palindromic, ");
            boolean isGapful = gap(i, inputNumLength);
            if (isGapful) propertiesList.add("gapful, ");
            boolean isEven = parity(i)[0];
            if (isEven) propertiesList.add("even");
            boolean isOdd = parity(i)[1];
            if (isOdd) propertiesList.add("odd");
            strList = String.join("", propertiesList);
            System.out.println(strList);
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        boolean gameExit = false;
        boolean validInput = false;
        long value1 = 0;
        long value2 = 0;
        printTheInstructions();
        Scanner scanner = new Scanner(System.in);
        String[] input;
        while (!gameExit) {
            while (!validInput) {
                try {
                    input = askForRequest(scanner);
                    if (input.equals("")) {
                        printTheInstructions();
                    }
                    else {
                        value1 = Long.parseLong(input[0]);
                        // If a user enters zero, terminate the program
                        if (value1 == 0) {
                            validInput = true;
                            gameExit = true;
                        } else if (value1 < 0) { // If numbers are not natural, print an error message;
                            System.out.println("The first parameter should be a natural number or zero.");
                        } else {
                            validInput = true;
                            // If one number is entered
                            if (input.length == 1) {
                                // calculate and print the properties of this number;
                                calculatePropertiesOne(value1);
                            } else {
                                // For two numbers, print the list of numbers with properties
                                value2 = Long.parseLong(input[1]);
                                if (value2 < 0) { // If numbers are not natural, print an error message;
                                    System.out.println("The second parameter should be a natural number or zero.");
                                } else {
                                    calculatePropertiesTwo(value1, value2);
                                }
                            }
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            // Once the request is processed, continue execution from step 3.
            value1 = 0;
            value2 = 0;
            validInput = false;
        }
        System.out.print("Goodbye!");
    }
}
