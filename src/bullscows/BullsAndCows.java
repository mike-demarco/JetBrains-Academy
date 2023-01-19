package bullscows;

import java.util.*;

public class BullsAndCows {

    public static String randomNumberGenerator(int length, int range) {
        // main char list
        List<Character> charList = new ArrayList<>(List.of(
                '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        // create char list in given range
        List<Character> charListInRange = charList.subList(0, range);
        // shuffle list
        Collections.shuffle(charListInRange);
        // result stringBuilder
        StringBuilder stringBuilder = new StringBuilder();
        // create sublist of given length
        for (var ch : charList.subList(0, length)) {
            stringBuilder.append(ch);
        }
        // return string of given length in given char range
        return stringBuilder.toString();
    }

    public static void playGame() {
        boolean victory = false;

        System.out.print("Input the length of the secret code:\n");
        int secretCodeLength = 0;

        try {
            secretCodeLength = new Scanner(System.in).nextInt();
            if (secretCodeLength < 1 || secretCodeLength > 36) {
                System.out.print("Error: secret code length must be between 1 and 36");
                return;
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid Input");
            return;
        }

        System.out.print("Input the number of possible symbols in the code:\n");
        int symbolRange = 0;
        char symbolRangeChar = 0;

        try {
            symbolRange = new Scanner(System.in).nextInt();

            if (symbolRange < secretCodeLength) {
                System.out.printf("Error: it's not possible to generate a code with a length of %d with " +
                        "%d unique symbols.", symbolRange, secretCodeLength);
                return;
            } else if (symbolRange < 1 || symbolRange > 36) {
                System.out.println("Error: Symbol Range must be etween 1 and 36");
                return;
            }
            symbolRangeChar = (char) symbolRange;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        } catch (Exception e) {
            System.out.printf("Error: minimum number of possible symbols is %d\n", secretCodeLength);
            return;
        }

        StringBuilder secretCode = new StringBuilder(randomNumberGenerator(secretCodeLength, symbolRange));
        System.out.println("The secret is prepared: ");
        System.out.printf("*".repeat(secretCodeLength));
        if (symbolRange < 11) {
            System.out.printf(" (0-%d).\n", symbolRange - 1);
        } else if (symbolRange == 11) {
            System.out.print(" (0-9, a).\n");
        } else if (symbolRange < 37) {
            System.out.printf(" (0-9, a-%c).\n", symbolRangeChar + 54);
        }

        // Set up game
        System.out.println("Okay, let's start a game!");
        int turnCounter = 1;

        while (!victory) {
            // init
            int countOfBulls = 0;
            int countOfCows = 0;
            int indexChar = 0;
            boolean validGuess = false;
            System.out.printf("Turn: %d\n", turnCounter);

            StringBuilder secretCodeGuess = null;
            do {
                try {
                    secretCodeGuess = new StringBuilder(new Scanner(System.in).nextLine());
                    validGuess = true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.printf("Error: it's not possible to generate a code with a length of %d with " +
                            "%d unique symbols.", secretCodeGuess.length(), secretCodeLength);
                    return;
                } catch (NumberFormatException e) {
                    System.out.printf("Error: %d isn't a valid number.\n", secretCodeGuess);
                    return;
                } catch (IllegalFormatConversionException e) {
                    System.out.printf("Error: can't accept secret code guess with a length of %d because " +
                            "less than %d.\n", secretCodeGuess.length(), secretCode);
                    return;
                }
            } while (!validGuess);

            // check bulls and cows
            do {
                try{
                    for (int i = 0; i < secretCodeLength; i++) {
                        if (i == indexChar) {
                            if (secretCodeGuess.charAt(indexChar) == secretCode.charAt(indexChar)) {
                                countOfBulls++;
                                break;
                            }
                        } else if (secretCodeGuess.charAt(indexChar) == secretCode.charAt(i)) {
                            countOfCows++;
                            break;
                        }
                    }
                    indexChar += 1;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.printf("Error: Guess must be of length %d", secretCodeLength);
                    return;
                }
            } while (indexChar < secretCodeLength);

            // print score
            if (countOfBulls == secretCodeLength) {
                System.out.printf("Grade: %d bulls.\n", secretCodeLength);
                victory = true;
            } else if (countOfBulls > 1 && countOfCows > 1) {
                System.out.printf("Grade: %d bulls and %d cows.\n", countOfBulls, countOfCows);
            } else if (countOfBulls == 0 && countOfCows == 0) {
                System.out.print("Grade: 0 bulls and 0 cows.\n");
            } else if (countOfBulls == 1 && countOfCows == 1) {
                System.out.print("Grade: 1 bull and 1 cow.\n");
            } else if (countOfBulls == 0 && countOfCows == 1) {
                System.out.print("Grade: 1 cow.\n");
            } else if (countOfBulls == 0 && countOfCows > 1) {
                System.out.printf("Grade: %d cows.\n", countOfCows);
            } else if (countOfBulls == 1 && countOfCows == 0) {
                System.out.print("Grade: 1 bull.\n");
            } else if (countOfBulls > 1 && countOfCows == 0) {
                System.out.printf("Grade: %d bulls.\n", countOfBulls);
            } else if (countOfBulls == 1 && countOfCows > 1) {
                System.out.printf("Grade: 1 bull and %d cows.\n", countOfCows);
            } else if (countOfBulls > 1 && countOfCows == 1) {
                System.out.printf("Grade: %d bulls and 1 cow.\n", countOfBulls);
            }
            turnCounter++;
        }

        // finish game
        System.out.print("Congratulations! You guessed the secret code.\n");
        return;
    }

    public static void main(String[] args) {
        playGame();
    }
}
