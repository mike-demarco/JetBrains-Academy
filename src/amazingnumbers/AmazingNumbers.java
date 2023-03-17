package amazingnumbers;

import java.util.*;

public class AmazingNumbers {
    public enum FilterEnum {
        EVEN("EVEN", "-EVEN"),
        ODD("ODD", "-ODD"),
        BUZZ("BUZZ", "-BUZZ"),
        DUCK("DUCK", "-DUCK"),
        PALINDROMIC("PALINDROMIC", "-PALINDROMIC"),
        GAPFUL("GAPFUL", "-GAPFUL"),
        SPY("SPY", "-SPY"),
        SQUARE("SQUARE", "-SQUARE"),
        SUNNY("SUNNY", "-SUNNY"),
        JUMPING("JUMPING", "-JUMPING"),
        HAPPY("HAPPY", "-HAPPY"),
        SAD("SAD", "-SAD");

        private String filter, negativeFilter;

        FilterEnum(String filter, String negativeFilter) {
            this.filter = filter;
            this.negativeFilter = negativeFilter;
        }

        public String getFilterString() {
            return filter;
        }

        public String getNegativeFilterFilterString() {
            return negativeFilter;
        }

        public static FilterEnum findByName(String name) {
            FilterEnum result = null;
            for (FilterEnum filter : values()) {
                if (filter.filter.equalsIgnoreCase(name)) {
                    result = filter;
                    break;
                } else if (filter.negativeFilter.equalsIgnoreCase(name)) {
                    result = filter;
                    break;
                }
            }
            return result;
        }

        public static boolean verifyByName(String name) {
            for (FilterEnum filter : values()) {
                if (filter.filter.equalsIgnoreCase(name)) {
                    return true;
                } else if (filter.negativeFilter.equalsIgnoreCase(name)) {
                    return true;
                }
            }
            return false;
        }

    }
    static boolean gameExit = false;
    static boolean inputProcessingComplete = false;
    static boolean validFilters = true;
    static int numOfArgs = 0;
    static String availableFiltersString =
            "[EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, JUMPING, HAPPY, SAD]";
    static long inputValue1 = 0;
    static long inputValue2 = 0;
    static List<String> filterList = new ArrayList<>(); // working list of filters to check against
    static List<String> invalidFilterList = new ArrayList<>(); // list of filters that are invalid
    static String propertyFilter = null;
    static int inputNumberLength = 0;
    static List<String> numberPropertiesString = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static String[] input;

    public static void printTheInstructions() {
        System.out.println("""
                Supported requests:
                - enter a natural number to know its properties;
                - enter two natural numbers to obtain the properties of the list:
                  * the first parameter represents a starting number;
                  * the second parameter shows how many consecutive numbers are to be printed;
                - two natural numbers and properties to search for;
                - a property preceded by minus must not be present in numbers;
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

    public static boolean isEven(long inputNumber) {
        return inputNumber % 2 == 0;
    }

    public static boolean isBuzz(long inputNumber) {
        boolean divisibleBy7 = inputNumber % 7 == 0;
        boolean endsWith7 = inputNumber % 10 == 7;
        return divisibleBy7 || endsWith7;
    }

    public static boolean isDuck(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();

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

    public static boolean isPalindrome(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();

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

    public static boolean isGap(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();

        if (inputNumberLength < 3) {
            return false;
        }

        long firstDigit = (long) Math.floor(inputNumber / Math.pow(10, inputNumberLength - 1) % 10);
        long lastDigit = inputNumber % 10;
        long gapNumberConcatenated = firstDigit * 10 + lastDigit;
        long gapNumberValue = inputNumber % gapNumberConcatenated;
        return gapNumberValue == 0;
    }

    public static boolean isSpy(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();

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

        return sumOfDigits == productOfDigits;
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

    public static boolean isJumping(long inputNumber) {
        if (inputNumber > 1234567898 && inputNumber < 2101010101) {
            return false;
        }
        inputNumberLength = String.valueOf(inputNumber).length();

        // Single-digit numbers are considered Jumping numbers.
        if (inputNumberLength == 1) {
            return true;
        }
        int tempDigitLeft, tempDigitRight;
        int indexCount = 1;
        boolean jumping = true;
        boolean efficientLoop = false;
        int endTempDigitLeft, endTempDigitRight;
        if (inputNumberLength >= 4) {
            efficientLoop = true;
        }

        for (long i = 1; i < Math.pow(10, inputNumberLength - 1); i *= 10) {
            // check
            tempDigitLeft = (int) (inputNumber / Math.pow(10, inputNumberLength - indexCount) % 10);
            tempDigitRight = (int) (inputNumber / Math.pow(10, inputNumberLength - indexCount - 1) % 10);
            if (tempDigitLeft != tempDigitRight + 1 && tempDigitLeft != tempDigitRight - 1) {
                jumping = false;
                break;
            }
            if (efficientLoop) {
                endTempDigitLeft = (int) (inputNumber / Math.pow(10, indexCount) % 10);
                endTempDigitRight = (int) (inputNumber / Math.pow(10, indexCount - 1) % 10);
                if (endTempDigitLeft != endTempDigitRight + 1 && endTempDigitLeft != endTempDigitRight - 1) {
                    jumping = false;
                    break;
                }
            }
            indexCount++;
        }
        return jumping;
    }

    static long numSquareSum(long n) {
        long squareSum = 0;
        while (n!= 0)
        {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

    //  method return true if n is Happy number
    static boolean isHappy(long n) {
        long slow, fast;

        //  initialize slow and fast by n
        slow = fast = n;
        do
        {
            //  move slow number
            // by one iteration
            slow = numSquareSum(slow);

            //  move fast number
            // by two iteration
            fast = numSquareSum(numSquareSum(fast));

        }
        while (slow != fast);

        //  if both number meet at 1,
        // then return true
        return (slow == 1);
    }

    public static boolean validProperty(String property) {
        return FilterEnum.verifyByName(property);
    }

    public static void propertyError(List properties) {
        // Generate and print Error String
        if (properties.size() == 1) {
            String filter = (String) properties.get(0);
            String capitalFilter = filter.toUpperCase(Locale.ROOT);
            System.out.println("The property [" + capitalFilter + "] is wrong.\n" +
                    "Available properties: " + availableFiltersString);
        } else {
            List<String> errorFilterList = new ArrayList<>();
            for (Object filter : properties) {
                String filterString = (String) filter;
                String capitalProperty = filterString.toUpperCase(Locale.ROOT);
                if (!FilterEnum.verifyByName(capitalProperty)) {
                    errorFilterList.add(capitalProperty);
                }
            }
            System.out.println("The properties " + errorFilterList + " are wrong.\n" +
                    "Available properties: " + availableFiltersString);
            errorFilterList.clear();
        }
    }

    public static boolean mutuallyExclusive (String first, String second) {
        return (((first.equalsIgnoreCase("EVEN") || second.equalsIgnoreCase("EVEN")) &&
                (first.equalsIgnoreCase("ODD") || second.equalsIgnoreCase("ODD"))) ||
                ((first.equalsIgnoreCase("DUCK") || second.equalsIgnoreCase("DUCK")) &&
                        (first.equalsIgnoreCase("SPY") || second.equalsIgnoreCase("SPY"))) ||
                ((first.equalsIgnoreCase("SQUARE") || second.equalsIgnoreCase("SQUARE")) &&
                        (first.equalsIgnoreCase("SUNNY") || second.equalsIgnoreCase("SUNNY"))) ||
                ((first.equalsIgnoreCase("HAPPY") || second.equalsIgnoreCase("HAPPY")) &&
                        (first.equalsIgnoreCase("SAD") || second.equalsIgnoreCase("SAD"))) ||
                ((first.equalsIgnoreCase("-EVEN") || second.equalsIgnoreCase("-EVEN")) &&
                        (first.equalsIgnoreCase("-ODD") || second.equalsIgnoreCase("-ODD"))) ||
                ((first.equalsIgnoreCase("-DUCK") || second.equalsIgnoreCase("-DUCK")) &&
                        (first.equalsIgnoreCase("-SPY") || second.equalsIgnoreCase("-SPY"))) ||
                ((first.equalsIgnoreCase("-SUNNY") || second.equalsIgnoreCase("-SUNNY")) &&
                        (first.equalsIgnoreCase("-SQUARE") || second.equalsIgnoreCase("-SQUARE"))) ||
                ((first.equalsIgnoreCase("-HAPPY") || second.equalsIgnoreCase("-HAPPY")) &&
                        (first.equalsIgnoreCase("-SAD") || second.equalsIgnoreCase("-SAD"))) ||
                ((first.equalsIgnoreCase("EVEN") || second.equalsIgnoreCase("EVEN")) &&
                        (first.equalsIgnoreCase("-EVEN") || second.equalsIgnoreCase("-EVEN"))) ||
                ((first.equalsIgnoreCase("ODD") || second.equalsIgnoreCase("ODD")) &&
                        (first.equalsIgnoreCase("-ODD") || second.equalsIgnoreCase("-ODD"))) ||
                ((first.equalsIgnoreCase("BUZZ") || second.equalsIgnoreCase("BUZZ")) &&
                        (first.equalsIgnoreCase("-BUZZ") || second.equalsIgnoreCase("-BUZZ"))) ||
                ((first.equalsIgnoreCase("DUCK") || second.equalsIgnoreCase("DUCK")) &&
                        (first.equalsIgnoreCase("-DUCK") || second.equalsIgnoreCase("-DUCK"))) ||
                ((first.equalsIgnoreCase("PALINDROMIC") || second.equalsIgnoreCase("PALINDROMIC")) &&
                        (first.equalsIgnoreCase("-PALINDROMIC") || second.equalsIgnoreCase("-PALINDROMIC"))) ||
                ((first.equalsIgnoreCase("GAPFUL") || second.equalsIgnoreCase("GAPFUL")) &&
                        (first.equalsIgnoreCase("-GAPFUL") || second.equalsIgnoreCase("-GAPFUL"))) ||
                ((first.equalsIgnoreCase("SPY") || second.equalsIgnoreCase("SPY")) &&
                        (first.equalsIgnoreCase("-SPY") || second.equalsIgnoreCase("-SPY"))) ||
                ((first.equalsIgnoreCase("SQUARE") || second.equalsIgnoreCase("SQUARE")) &&
                        (first.equalsIgnoreCase("-SQUARE") || second.equalsIgnoreCase("-SQUARE"))) ||
                ((first.equalsIgnoreCase("SUNNY") || second.equalsIgnoreCase("SUNNY")) &&
                        (first.equalsIgnoreCase("-SUNNY") || second.equalsIgnoreCase("-SUNNY"))) ||
                ((first.equalsIgnoreCase("JUMPING") || second.equalsIgnoreCase("JUMPING")) &&
                        (first.equalsIgnoreCase("-JUMPING") || second.equalsIgnoreCase("-JUMPING"))) ||
                ((first.equalsIgnoreCase("HAPPY") || second.equalsIgnoreCase("HAPPY")) &&
                        (first.equalsIgnoreCase("-HAPPY") || second.equalsIgnoreCase("-HAPPY"))) ||
                ((first.equalsIgnoreCase("SAD") || second.equalsIgnoreCase("SAD")) &&
                        (first.equalsIgnoreCase("-SAD") || second.equalsIgnoreCase("-SAD")))
        );
    }

    public static void mutuallyExclusiveError (String first, String second) {
        System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                "There are no numbers with these properties.\n", first, second);
    }

    public static void generateNumberProperties(long i) {
        numberPropertiesString.add(i + " is ");

        if (isEven(i)) {
            numberPropertiesString.add("even, ");
        } else {
            numberPropertiesString.add("odd, ");
        }
        if (isBuzz(i)) {
            numberPropertiesString.add("buzz, ");
        }
        if (isDuck(i)) {
            numberPropertiesString.add("duck, ");
        }
        if (isPalindrome(i)) {
            numberPropertiesString.add("palindromic, ");
        }
        if (isGap(i)) {
            numberPropertiesString.add("gapful, ");
        }
        if (isSpy(i)) {
            numberPropertiesString.add("spy, ");
        }
        if (isSquare(i)) {
            numberPropertiesString.add("square, ");
        }
        if (isSunny(i)) {
            numberPropertiesString.add("sunny, ");
        }
        if (isJumping(i)) {
            numberPropertiesString.add("jumping, ");
        }
        if (isHappy(i)) {
            numberPropertiesString.add("happy");
        } else {
            numberPropertiesString.add("sad");
        }
    }

    public static boolean propertyCheck(long currentNumber, String property) {

        switch (property) {
            case "BUZZ" -> {
                if (isBuzz(currentNumber)) {
                    return true;
                }
            }
            case "-BUZZ" -> {
                if (!isBuzz(currentNumber)) {
                    return true;
                }
            }
            case "DUCK" -> {
                if (isDuck(currentNumber)) {
                    return true;
                }
            }
            case "-DUCK" -> {
                if (!isDuck(currentNumber)) {
                    return true;
                }
            }
            case "PALINDROMIC" -> {
                if (isPalindrome(currentNumber)) {
                    return true;
                }
            }
            case "-PALINDROMIC" -> {
                if (!isPalindrome(currentNumber)) {
                    return true;
                }
            }
            case "GAPFUL" -> {
                if (isGap(currentNumber)) {
                    return true;
                }
            }
            case "-GAPFUL" -> {
                if (!isGap(currentNumber)) {
                    return true;
                }
            }
            case "SPY" -> {
                if (isSpy(currentNumber)) {
                    return true;
                }
            }
            case "-SPY" -> {
                if (!isSpy(currentNumber)) {
                    return true;
                }
            }
            case "SQUARE" -> {
                if (isSquare(currentNumber)) {
                    return true;
                }
            }
            case "-SQUARE" -> {
                if (!isSquare(currentNumber)) {
                    return true;
                }
            }
            case "SUNNY" -> {
                if (isSunny(currentNumber)) {
                    return true;
                }
            }
            case "-SUNNY" -> {
                if (!isSunny(currentNumber)) {
                    return true;
                }
            }
            case "JUMPING" -> {
                if (isJumping(currentNumber)) {
                    return true;
                }
            }
            case "-JUMPING" -> {
                if (!isJumping(currentNumber)) {
                    return true;
                }
            }
            case "HAPPY", "-SAD" -> {
                if (isHappy(currentNumber)) {
                    return true;
                }
            }
            case "-HAPPY", "SAD" -> {
                if (!isHappy(currentNumber)) {
                    return true;
                }
            }
            case "EVEN", "-ODD" -> {
                if (isEven(currentNumber)) {
                    return true;
                }
            }
            case "ODD", "-EVEN" -> {
                if (!isEven(currentNumber)) {
                    return true;
                }
            }
            default -> {
                return false;
            }
        }
        return false;
    }

    public static void calculatePropertiesOne(long inputNumber) {
        inputNumberLength = String.valueOf(inputNumber).length();
        boolean isHappy = isHappy(inputNumber);
        boolean isEven = isEven(inputNumber);
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
                        happy: %b
                          sad: %b
                         even: %b
                          odd: %b
                """, inputNumber, isBuzz(inputNumber), isDuck(inputNumber),
                isPalindrome(inputNumber), isGap(inputNumber),
                isSpy(inputNumber), isSquare(inputNumber), isSunny(inputNumber),
                isJumping(inputNumber), isHappy, !isHappy,
                isEven, !isEven);
    }

    public static void calculatePropertiesTwo(long number, long count) {
        long finalNumberChecked = number + count;

        for (long i = number; i < finalNumberChecked; i++) {
            generateNumberProperties(i);
            System.out.println(String.join("", numberPropertiesString));
            numberPropertiesString.clear();
        }
    }

    public static void calculatePropertiesFiltered(long number, long count, List filterList) {
        long printedNumberCount = 0;
        boolean matchesFilterList = true;

        // for each number
        //      for each filter in given list
        //          if number has all filters
        //              run properties list, generate string, print
        for (long i = number; printedNumberCount < count; i++) {
            for (Object filter : filterList) {
                String capitalizedFilter = filter.toString().toUpperCase();
                if (!propertyCheck(i, (String) capitalizedFilter)) {
                    matchesFilterList = false;
                    break;
                }
            }
            if (matchesFilterList) {
                generateNumberProperties(i);
                System.out.println(String.join("", numberPropertiesString));
                numberPropertiesString.clear();
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
                validFilters = true;
                try {
                    input = askForRequest(scanner);
                    if (input.equals("")) {
                        printTheInstructions();
                    } else {
                        if (numOfArgs > 0) { // If one number is entered
                            inputValue1 = convertStringToLong(input[0]);
                            if (inputValue1 == 0) { // If a user enters zero, terminate the program
                                inputProcessingComplete = true;
                                gameExit = true;
                            } else if (inputValue1 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The first parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 1) { // calculate and print the properties of this number;
                                    inputProcessingComplete = true;
                                    calculatePropertiesOne(inputValue1);
                                }
                            }
                        }
                        if (numOfArgs > 1 && !inputProcessingComplete) { // If two numbers are entered
                            inputValue2 = convertStringToLong(input[1]);
                            if (inputValue2 < 0) { // If numbers are not natural, print an error message
                                System.out.println("The second parameter should be a natural number or zero.");
                            } else {
                                if (numOfArgs == 2) { // calculate and print properties of this number range
                                    inputProcessingComplete = true;
                                    calculatePropertiesTwo(inputValue1, inputValue2);
                                }
                            }
                        }
                        if (numOfArgs > 2 && !inputProcessingComplete) { // For numbers in range, handle filter(s) given
                            for (int i = 2; i < numOfArgs; i++) { // Starting position, input arg 3, the first filter
                                propertyFilter = input[i].toUpperCase(Locale.ROOT);
                                filterList.add(propertyFilter);
                            }
                            int numOfFilters = filterList.size();
                            if (numOfFilters > 1) { // check all combinations of filters for mutually exclusive pairs
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
                            if (validFilters) {
                                for (int i = 2; i < numOfArgs; i++) { // Starting position, input arg 3, the first filter
                                    // check validity of each filter
                                    propertyFilter = input[i];
                                    if (!validProperty(propertyFilter)) {
                                        invalidFilterList.add(propertyFilter);
                                        validFilters = false;
                                    }
                                }
                                if (!validFilters) {
                                    propertyError(invalidFilterList);
                                }
                            }
                            if (validFilters) { // calculate and print the properties of this number range;
                                inputProcessingComplete = true;
                                calculatePropertiesFiltered(inputValue1, inputValue2, filterList);
                            }
                        }
                    }
                } catch(NumberFormatException e) {
                    System.out.println("The first parameter should be a natural number or zero.");
                }
            }
            // Once the request is processed, continue execution from step 3. Reset static variables
            inputValue1 = 0;
            inputValue2 = 0;
            propertyFilter = null;
            inputProcessingComplete = false;
            validFilters = true;
            inputNumberLength = 0;
            filterList.clear();
            invalidFilterList.clear();
        }
        System.out.print("Goodbye!");
    }
}
