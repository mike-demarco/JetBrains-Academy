package amazingnumbers;

import java.util.*;

public class AmazingNumbers {
    static boolean gameExit = false;
    static boolean inputProcessingComplete = false;
    static boolean validFilters = false;
    static int numOfArgs = 0;
    public enum FilterEnum {
        EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING;
    }
    static String filterEnumString = Arrays.toString(FilterEnum.values());
    static long value1 = 0;
    static long value2 = 0;
    static List<String> filterList = new ArrayList<>(); // working list of filters to check against
    static String propertyFilter = null;
    static int inputNumberLength = 0;
    static List<String> printedPropertiesString = new ArrayList<>();
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

    public static Long convertStringToLong(String str) {
        try {
            return Long.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
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
        // Single-digit numbers are considered Jumping numbers.
        if (inputNumberLength == 1) {
            return true;
        }
        int tempDigitLeft, tempDigitRight;
        int indexCount = 1;
        boolean jumping = true;

        for (long i = 1; i < Math.pow(10, inputNumberLength - 1); i *= 10) {
            // check
            tempDigitLeft = (int) (inputNumber / Math.pow(10, inputNumberLength - indexCount) % 10);
            tempDigitRight = (int) (inputNumber / Math.pow(10, inputNumberLength - indexCount - 1) % 10);
            if (tempDigitLeft != tempDigitRight + 1 && tempDigitLeft != tempDigitRight - 1) {
                jumping = false;
                break;
            }
            indexCount++;
        }
        return jumping;
    }

    public static boolean validProperty(String property) {
        // If a user inputs an incorrect property,
        // print the error message and the list of available properties;
        // property names include even, odd, buzz, duck, palindromic, gapful, spy, and sunny
        return filterEnumString.contains(property.toUpperCase(Locale.ROOT));
    }

    public static void propertyError(List properties) {
        if (properties.size() == 1) {
            String filter = (String) properties.get(0);
            String capitalFilter = filter.toUpperCase(Locale.ROOT);
            System.out.println("The property [" + capitalFilter + "] is wrong.\nAvailable properties: " + filterEnumString);
        } else {
            List<String> filterList = new ArrayList<>();
            for (Object filter : properties) {
                String filterString = (String) filter;
                String capitalProperty = filterString.toUpperCase(Locale.ROOT);
                filterList.add(capitalProperty);
            }
            System.out.println("The properties " + filterList + " are wrong.\nAvailable properties: " + filterEnumString);
            filterList.clear();
        }
    }

    public static boolean mutuallyExclusive(String first, String second) {
        String masterKey = first.toUpperCase() + second.toUpperCase();
        return (masterKey.contains("EVEN") && masterKey.contains("ODD")) ||
                (masterKey.contains("DUCK") && masterKey.contains("SPY")) ||
                (masterKey.contains("SUNNY") && masterKey.contains("SQUARE"));
    }

    public static void mutuallyExclusiveError(String first, String second) {
        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                "There are no numbers with these properties.\n", first, second);
    }

    public static void propertiesCheck(long i) {
        inputNumberLength = String.valueOf(i).length();
        printedPropertiesString.add(i + " is ");

        if (isBuzz(i)) {
            printedPropertiesString.add("buzz, ");
        }
        if (isDuck(i, inputNumberLength)) {
            printedPropertiesString.add("duck, ");
        }
        if (isPalindrome(i, inputNumberLength)) {
            printedPropertiesString.add("palindromic, ");
        }
        if (isGap(i, inputNumberLength)) {
            printedPropertiesString.add("gapful, ");
        }
        if (isSpy(i, inputNumberLength)) {
            printedPropertiesString.add("spy, ");
        }
        if (isSquare(i)) {
            printedPropertiesString.add("square, ");
        }
        if (isSunny(i)) {
            printedPropertiesString.add("sunny, ");
        }
        if (isJumping(i, inputNumberLength)) {
            printedPropertiesString.add("jumping, ");
        }
        if (parity(i)[0]) {
            printedPropertiesString.add("even");
        }
        if (parity(i)[1]) {
            printedPropertiesString.add("odd");
        }
    }

    public static boolean propertyCheck(long i, String property) {
        inputNumberLength = String.valueOf(i).length();

        switch (property) {
            case "buzz":
                if (isBuzz(i)) {
                    return true;
                }
                break;
            case "duck":
                if (isDuck(i, inputNumberLength)) {
                    return true;
                }
                break;
            case "palindrome":
                if (isPalindrome(i, inputNumberLength)) {
                    return true;
                }
                break;
            case "gap":
                if (isGap(i, inputNumberLength)) {
                    return true;
                }
                break;
            case "spy":
                if (isSpy(i, inputNumberLength)) {
                    return true;
                }
                break;
            case "square":
                if (isSquare(i)) {
                    return true;
                }
                break;
            case "sunny":
                if (isSunny(i)) {
                    return true;
                }
                break;
            case "jumping":
                if (isJumping(i, inputNumberLength)) {
                    return true;
                }
                break;
            case "even":
                if (parity(i)[0]) {
                    return true;
                }
                break;
            case "odd":
                if (parity(i)[1]) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
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

        for (long i = number; i < finalNumberChecked; i++) {
            propertiesCheck(i);
            System.out.println(String.join("", printedPropertiesString));
            printedPropertiesString.clear();
        }
    }

    public static void calculatePropertiesFiltered(long number, long count, List filterList) {
        long printedNumberCount = 0;
        boolean matchesFilterList = true;
        int filterCount = filterList.size();

        // for each number
        //      for each filter in given list
        //          if number has all filters
        //              run properties list, generate string, print
        for (long i = number; printedNumberCount < count; i++) {
            for (Object filter : filterList) {
                if (!propertyCheck(i, (String) filter)) {
                    matchesFilterList = false;
                    break;
                }
            }
            if (matchesFilterList) {
                propertiesCheck(i);
                System.out.println(String.join("", printedPropertiesString));
                printedPropertiesString.clear();
                printedNumberCount++;
            }
            matchesFilterList = true;
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Amazing Numbers!");
        printTheInstructions();

        while (!gameExit) {
            while (!inputProcessingComplete) {
                try {
                    input = askForRequest(scanner);
                    if (input.equals("")) {
                        printTheInstructions();
                    } else {
                        if (numOfArgs > 0) { // If one number is entered
                            value1 = convertStringToLong(input[0]);
                            if (value1 == 0) { // If a user enters zero, terminate the program
                                inputProcessingComplete = true;
                                gameExit = true;
                            } else if (value1 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The first parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 1) { // calculate and print the properties of this number;
                                    inputProcessingComplete = true;
                                    calculatePropertiesOne(value1);
                                }
                            }
                        }
                        if (numOfArgs > 1 && !inputProcessingComplete) { // If two numbers are entered
                            value2 = convertStringToLong(input[1]);
                            if (value2 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The second parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 2) { // calculate and print properties of this number range
                                    inputProcessingComplete = true;
                                    calculatePropertiesTwo(value1, value2);
                                }
                            }
                        }
                        if (numOfArgs > 2 && !inputProcessingComplete) { // For numbers in range, handle filter(s) given
                            for (int i = 2; i < numOfArgs; i++) { // Starting position, input arg 3, the first filter
                                // check validity of each filter
                                propertyFilter = input[i];
                                if (!validProperty(propertyFilter)) {
                                    filterList.add(propertyFilter);
                                    validFilters = false;
                                } else { // add filter to list
                                    filterList.add(propertyFilter);
                                    validFilters = true;
                                }
                            }
                            if (!validFilters) {
                                propertyError(filterList);
                            }
                            int numOfFilters = filterList.size();
                            if (validFilters && numOfFilters > 1) { // check all combinations of filters for mutually exclusive pairs
                                for (int i = 0; i < numOfFilters; i++) {
                                    for (int j = i + 1; j < numOfFilters; j++) {
                                        if (mutuallyExclusive(filterList.get(i), filterList.get(j))) {
                                            mutuallyExclusiveError(filterList.get(i), filterList.get(j));
                                            validFilters = false;
                                            break;
                                        }
                                    }
                                    if (!validFilters) {
                                        filterList.clear();
                                        break;
                                    }
                                }
                            }
                            if (validFilters) { // calculate and print the properties of this number range;
                                inputProcessingComplete = true;
                                calculatePropertiesFiltered(value1, value2, filterList);
                            }
                        }
                    }
                } catch(NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            // Once the request is processed, continue execution from step 3. Reset static variables
            value1 = 0;
            value2 = 0;
            propertyFilter = null;
            inputProcessingComplete = false;
            validFilters = true;
            inputNumberLength = 0;
        }
        System.out.print("Goodbye!");
    }
}
