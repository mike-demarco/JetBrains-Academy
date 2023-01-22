package rockpaperscissors;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static Scanner scanner = new Scanner(System.in);
    public static String[] choices = new String[] {"rock", "paper", "scissors", "rock", "paper", "scissors"};
    public static String playerChoice, computerChoice;
    public static Random random = new Random();
    public static boolean playerWon = false, computerWon = false, gameOver = false;
    public static String[] gameResultStrings = new String[] {
            "Well done. The computer chose %s and failed\n",
            "There is a draw (%s)\n",
            "Sorry, but the computer chose %s\n"
    };

    public static void takeInput(RockPaperScissors game) {
        boolean validInput = false;
        do {
            playerChoice = scanner.nextLine().toLowerCase();
            if (playerChoice.equals("!exit")) {
                gameOver = true;
                return;
            } else if (!Arrays.asList(choices).contains(playerChoice)) System.out.println("Invalid input");
            else validInput = true;
        } while (!validInput);
        computerChoice = choices[random.nextInt(3)];
    }

    public static void printResult(int index) {
        System.out.printf(gameResultStrings[index], computerChoice);
    }

    public static void determineOutcome(RockPaperScissors game) {
        // cycle through combinations
        if (playerChoice.equals(computerChoice)) {
            printResult(1);
            return;
        } else {
            for (int i = 0; i < choices.length - 3; i++) {
                if (playerChoice.equals(choices[i]) && computerChoice.equals(choices[i + 2])) {
                    printResult(0);
                    return;
                } else if (playerChoice.equals(choices[i]) && computerChoice.equals(choices[i + 1])) {
                    printResult(2);
                    return;
                }
            }
        }
    }

    public static void main (String[]args){
        RockPaperScissors game = new RockPaperScissors();
        while (!gameOver) {
            takeInput(game);
            if (gameOver) break;
            determineOutcome(game);
        }
        System.out.print("Bye!");
    }
}
