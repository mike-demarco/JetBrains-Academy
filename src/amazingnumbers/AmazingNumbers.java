package amazingnumbers;

import java.util.*;
import java.util.stream.Collectors;

public class AmazingNumbers {
    static boolean gameExit = false;
    static boolean validInput = false;
    static boolean validFilter = false;
    static int numOfArgs = 0;

    public enum properties {
        EVEN,
        ODD,
        BUZZ,
        DUCK,
        PALINDROMIC,
        GAPFUL,
        SPY,
        SQUARE,
        SUNNY,
        JUMPING;
    }

    String property;

    properties(String property) {
        this.property = property;
    }


    // Build the set.
    final Set<String> mySet = Arrays.stream(properties.values()).map(Enum::name).collect(Collectors.toSet());

    static String availablePropertiesString =
            "Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING]";

    static long value1 = 0;
    static long value2 = 0;
    static boolean anotherPropertyExists = true;
    static List<String> filterList = new ArrayList<String>();
    static String propertyFilter = null;

    static int inputNumberLength = 0;
    static List<String> printedPropertiesString = new ArrayList<String>();

    static Scanner scanner = new Scanner(System.in);
    static String[] input;

    public static void printTheInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameters show how many consecutive numbers are to be processed;
                - two natural numbers and two properties to search for;
                - separate the parameters with one space;
                - enter 0 to exit.""");
    }

    public static String[] askForRequest(Scanner scanner) {
        System.out.println("Enter a request:");
        String[] input = scanner.nextLine().split(" ");
        numOfArgs = input.length;
        return input;
    }

    public static boolean[] parity(long inputNumber) {
        boolean isEven = inputNumber % 2 == 0;
        boolean isOdd = inputNumber % 2 == 1;
        boolean[] parity = new boolean[] {isEven, isOdd};
        return parity;
    }

    public static boolean isBuzz(long inputNumber) {
        boolean divisibleBy7 = inputNumber % 7 == 0;
        boolean endsWith7 = inputNumber % 10 == 7;
        return divisibleBy7 || endsWith7;
    }

    public static boolean isDuck(long inputNumber, int inputNumberLength) {
        int indexNumLength = 1;
        for (long i = 1; indexNumLength < inputNumberLength; i *= 10) {
            long index = inputNumber / i % 10;
            boolean check = index == 0;
            indexNumLength = String.valueOf(i).length();
            if (check) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPalindrome(long inputNumber, int inputNumberLength) {
        int tempDigitLeft, tempDigitRight, iLength;
        int halfNumberLength = inputNumberLength / 2 + 1;
        int counter = 1;
        for (long i = 1; i < Math.pow(10, halfNumberLength); i *= 10) {
            // escape check
            iLength = String.valueOf(i).length();
            if (iLength == halfNumberLength) {
                return true;
            }

            // check
            tempDigitLeft = (int) (inputNumber / Math.pow(10, inputNumberLength - counter) % 10);
            tempDigitRight = (int) (inputNumber / (double) i % 10);
            if (tempDigitLeft != tempDigitRight) {
                break;
            } else {
                counter++;
            }
        }
        return false;
    }

    public static boolean isGap(long inputNumber, int inputNumberLength) {
        if (inputNumberLength < 3) {
            return false;
        }

        long firstDigit = (long) Math.floor(inputNumber / Math.pow(10, inputNumberLength - 1) % 10);
        long lastDigit = inputNumber % 10;
        long gapNumberConcatenated = firstDigit * 10 + lastDigit;
        long gapNumberValue = inputNumber % gapNumberConcatenated;
        return gapNumberValue == 0;
    }

    public static boolean isSpy(long inputNumber, int inputNumberLength) {
        if (inputNumberLength == 1) {
            return true;
        }

        int sumOfDigits = 0;
        int productOfDigits = 1;
        int tempDigit;

        for (int i = 1; i < inputNumberLength + 1; i++) {
            tempDigit = (int) (inputNumber / Math.pow(10, inputNumberLength - i) % 10);
            sumOfDigits += tempDigit;
            productOfDigits *= tempDigit;
        }

        if (sumOfDigits == productOfDigits) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isSquare(long inputNumber) {
        long x = (long) Math.sqrt(inputNumber);
        return Math.pow(x, 2) == inputNumber;
    }

    public static boolean isSunny(long inputNumber) {
        if (inputNumber == 1) {
            return false;
        } else {
            long nPlusOne = inputNumber + 1;
            long x = (long) Math.sqrt(nPlusOne);
            return Math.pow(x, 2) == nPlusOne;
        }
    }

    public static boolean isJumping(long inputNumber, int inputNumberLength) {
        int tempDigitLeft, tempDigitRight, iLength;

        for (long i = 1; i < Math.pow(10, inputNumberLength - 1); i *= 10) {
            // check
            tempDigitLeft = (int) (inputNumber / Math.pow(10, inputNumberLength - i) % 10);
            tempDigitRight = (int) (inputNumber / Math.pow(10, inputNumberLength - i - 1) % 10);
            if (tempDigitLeft == tempDigitRight + 1 || tempDigitLeft == tempDigitRight - 1) {
                return true;
            }
        }
        return false;
    }

    public static boolean validProperty(String property) {
        // If a user inputs an incorrect property,
        // print the error message and the list of available properties;
        // property names include even, odd, buzz, duck, palindromic, gapful, spy, and sunny
        if (properties.contains(property.toUpperCase(Locale.ROOT))) {
            return true;
        } else {
            return false;
        }
    }

    public static void propertyError(String property) {
        String capitalProperty = property.toUpperCase(Locale.ROOT);
        System.out.println("The property [" + capitalProperty + "] is wrong.\n" + availableProperties);
    }

    public static void propertyError(String propertyA, String propertyB) {
        String masterKey = propertyA.toUpperCase() + ", " + propertyB.toUpperCase();
        System.out.println("The properties [" + masterKey + "] are wrong.\n" + availableProperties);
    }

    public static boolean mutuallyExclusive(String first, String second) {
        String masterKey = first.toUpperCase() + second.toUpperCase();
        return (masterKey.contains("EVEN") && masterKey.contains("ODD")) ||
                (masterKey.contains("DUCK") && masterKey.contains("SPY")) ||
                (masterKey.contains("SUNNY") && masterKey.contains("SQUARE"));
    }

    public static void mutuallyExclusiveError(String first, String second) {
        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                "There are no numbers with these properties.", first, second);
    }

    public static void propertiesCheck(List<String> propertiesList, long i) {
        inputNumberLength = String.valueOf(i).length();
        propertiesList.add(i + " is ");
        boolean isBuzz = isBuzz(i);
        if (isBuzz) {
            propertiesList.add("buzz, ");
        }
        boolean isDuck = isDuck(i, inputNumberLength);
        if (isDuck) {
            propertiesList.add("duck, ");
        }
        boolean isPalindromic = isPalindrome(i, inputNumberLength);
        if (isPalindromic) {
            propertiesList.add("palindromic, ");
        }
        boolean isGapful = isGap(i, inputNumberLength);
        if (isGapful) {
            propertiesList.add("gapful, ");
        }
        boolean isSpy = isSpy(i, inputNumberLength);
        if (isSpy) {
            propertiesList.add("spy, ");
        }
        boolean isSquare = isSquare(i);
        if (isSquare) {
            propertiesList.add("square, ");
        }
        boolean isSunny = isSunny(i);
        if (isSunny) {
            propertiesList.add("sunny, ");
        }
        boolean isEven = parity(i)[0];
        if (isEven) {
            propertiesList.add("even");
        }
        boolean isOdd = parity(i)[1];
        if (isOdd) {
            propertiesList.add("odd");
        }
    }

    public static void calculatePropertiesOne(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();
        System.out.printf("""
                 Properties of %d
                         buzz: %b
                         duck: %b
                  palindromic: %b
                         gapful: %b
                         spy: %b
                         square: %b
                         sunny: %b
                         jumping: %b
                         even: %b
                         odd: %b
                """, inputNumber, isBuzz(inputNumber), isDuck(inputNumber, inputNumberLength),
                isPalindrome(inputNumber, inputNumberLength), isGap(inputNumber, inputNumberLength),
                isSpy(inputNumber, inputNumberLength), isSquare(inputNumber), isSunny(inputNumber),
                isJumping(inputNumber, inputNumberLength), parity(inputNumber)[0], parity(inputNumber)[1]);
    }

    public static void calculatePropertiesTwo(long number, long count) {
        long finalNumberChecked = number + count;
        String strList;

        for (long i = number; i < finalNumberChecked; i++) {
            propertiesCheck(filterList, i);
            strList = String.join("", filterList);
            System.out.println(strList);
            filterList.clear();
        }
    }

    public static void calculatePropertiesFiltered(long number, long count, List filterList) {
        long matchCount = 0;
        String strList;

        for (long i = number; matchCount < count; i++) {
            propertiesCheck(filterList, i);
            strList = String.join("", filterList);
            if (strList.contains(filterList[i].toLowerCase()) && strList.contains(filterB.toLowerCase())) {
                System.out.println(strList);
                matchCount++;
            }
            filterList.clear();
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printTheInstructions();

        System.out.print("Goodbye!");
        while (!gameExit) {
            while (!validInput) {
                try {
                    input = askForRequest(scanner);
                    if (input.equals("")) {
                        printTheInstructions();
                    } else {
                        if (numOfArgs > 0) { // If one number is entered
                            value1 = Long.parseLong(input[0]);
                            if (value1 == 0) { // If a user enters zero, terminate the program
                                validInput = true;
                                gameExit = true;
                            } else if (value1 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The first parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 1) { // calculate and print the properties of this number;
                                    validInput = true;
                                    calculatePropertiesOne(value1);
                                }
                            }
                        }
                        if (numOfArgs > 1 && !validInput) { // If two numbers are entered
                            value2 = Long.parseLong(input[1]);
                            if (value2 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The second parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 2) { // calculate and print properties of this number range
                                    validInput = true;
                                    calculatePropertiesTwo(value1, value2);
                                }
                            }
                        }
                        if (numOfArgs > 2 && !validInput) { // For numbers in range, handle filter(s) given
                            for (int i = 2; i < numOfArgs; i++) {
                                // check validity of each filter
                                propertyFilter = input[i];
                                if (!validProperty(propertyFilter)) {
                                    propertyError(propertyFilter);
                                    validFilter = false;
                                    break;
                                } else { // add filter to list
                                    filterList.add(input[i]);
                                    validFilter = true;
                                }
                            }
                            int numOfFilters = filterList.size();
                            if (validFilter) {
                                for (int i = 0; i < numOfFilters; i++) {
                                    for (int j = i + 1; j < numOfFilters; j++) {
                                        if (mutuallyExclusive(filterList.get(i), filterList.get(j))) {
                                            mutuallyExclusiveError(filterList.get(i), filterList.get(j));
                                            validFilter = true;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (validFilter) { // calculate and print the properties of this number range;
                                validInput = true;
                                calculatePropertiesFiltered(value1, value2, filterList);
                            }
                        }
                    }
                } catch(NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
        }
        // Once the request is processed, continue execution from step 3.
        value1 = 0;
        value2 = 0;
        propertyFilter = null;
        validInput = false;
        inputNumberLength = 0;
    }
        switch (propertyFilter) {
        case EVEN:
            break;
        case ODD:
            break;
        case BUZZ:
            break;
        case DUCK:
            break;
        case PALINDROMIC:
            break;
        case GAPFUL:
            break;
        case SPY:
            break;
        case SQUARE:
            break;
        case SUNNY:
            break;
        case JUMPING:
            break;
        default:
            System.out.println(availablePropertiesString);
    }
}
