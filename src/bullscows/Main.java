package bullscows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static String randomNumberGenerator(int length) {
        List<Integer> randomList = new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        while (randomList.get(0) == 0) {
            Collections.shuffle(randomList);
        }

        StringBuilder result = new StringBuilder();
        for (var ch : randomList.subList(0, length)) {
            result.append(ch);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        Boolean victory = false;

        System.out.print("Please, enter the secret code's length:\n");
        Integer secretCodeLength = new Scanner(System.in).nextInt();

        if (secretCodeLength > 10) {
            System.out.print(String.format(
                    "Error: can't generate a secret number with a length of %d because " +
                            "there aren't enough unique digits.", secretCodeLength));
        } else {
            // Set up game
            System.out.print("Okay, let's start a game!\n");
            StringBuilder secretCode = new StringBuilder(randomNumberGenerator(secretCodeLength));
            int turnCounter = 1;

            while (!victory) {

                // init
                int countOfBulls = 0;
                int countOfCows = 0;
                int indexChar = 0;
                System.out.printf("\nTurn: %d\n", turnCounter);

                StringBuilder secretCodeGuess = new StringBuilder(new Scanner(System.in).nextLine());

                // check bulls and cows
                do {
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
        }
    }
}
