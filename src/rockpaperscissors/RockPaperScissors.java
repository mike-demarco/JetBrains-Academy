package rockpaperscissors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class RockPaperScissors {
    private static final Path FILE_PATH = Path.of("src/rockpaperscissors/rating.txt");
    public static Scanner scanner = new Scanner(System.in);
    public static String playerName;
    public static int playerScore = 0;
    public static String[] options = new String[] {"rock", "paper", "scissors", "rock", "paper", "scissors"};
    public static int countOfOptions = 3;
    public static String playerChoice, computerChoice;
    public static Random random = new Random();
    public static boolean gameOver = false;
    public static String[] gameResultStrings = new String[] {
            "Well done. The computer chose %s and failed\n",
            "There is a draw (%s)\n",
            "Sorry, but the computer chose %s\n"
    };

    public static void enterPlayerName() {
        System.out.print("Enter your name: ");
        playerName = scanner.nextLine();
        System.out.printf("Hello, %s", playerName);
        System.out.println("");
    }

    public static void updateRatingFile() {
        boolean newName = true;

        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(FILE_PATH, StandardCharsets.UTF_8));

            for (int i = 0; i < fileContent.size(); i++) {
                if (fileContent.get(i).contains(playerName)) {
                    String[] indexPlayer = fileContent.get(i).split(" ");
                    if (!gameOver) playerScore = Integer.parseInt(indexPlayer[1]);
                    fileContent.set(i, playerName + " " + playerScore);
                    newName = false;
                    break;
                }
            }
            if (newName) fileContent.add(playerName + " " + playerScore);
            Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
    }

    public static void enterPlayerOptions() {
        String input = scanner.nextLine();
        if (!input.isEmpty()) {
            String[] inputtedOptions = input.split(",");
            countOfOptions = inputtedOptions.length;
            options = Arrays.copyOf(inputtedOptions, countOfOptions * 2);
            System.arraycopy(inputtedOptions, 0, options, countOfOptions, countOfOptions);
        }
    }

    public static void takeInput() {
        boolean validInput = false;

        do {
            String input = scanner.nextLine();
            playerChoice = input.toLowerCase();
            if (playerChoice.equals("!exit")) {
                // Update player score in rating.txt file
                gameOver = true;
                updateRatingFile();
                return;
            } else if (playerChoice.equals("!rating")) {
                System.out.printf("Your rating: %d\n", playerScore);
            } else if (!Arrays.asList(options).contains(playerChoice)) System.out.println("Invalid input");
            else validInput = true;
        } while (!validInput);
    }

    public static void printResult(int index) {
        System.out.printf(gameResultStrings[index], computerChoice);
    }

    public static void determineOutcome() {
        // define computer choice
        computerChoice = options[random.nextInt(countOfOptions)];

        // cycle through combinations
        if (playerChoice.equals(computerChoice)) {
            // draw
            printResult(1);
            playerScore += 50;
        } else {
            // define index of player choice in list of options
            int index = 0;
            boolean playerWin = true;
            for (int i = 0; i < countOfOptions; i++) {
                if (playerChoice.equals(options[i])) {
                    index = i;
                    break;
                }
            }
            for (int i = index + 1; i < (countOfOptions / 2) + index + 1; i++) {
                // if computer choice equals front half of subsequent options, player loses
                if (computerChoice.equals(options[i])) {
                    // loss
                    printResult(2);
                    return;
                }
            }
            // else, player wins
            printResult(0);
            playerScore += 100;
        }
    }

    public static void main (String[]args) throws IOException {
        // Enter player name
        enterPlayerName();
        // Update rating file
        updateRatingFile();
        // Enter player options
        enterPlayerOptions();
        // Game loop. While game is not over
        System.out.println("Okay, let's start");
        while (!gameOver) {
            // take input from player
            takeInput();
            // if player enters !exit, then the game is over, break from game loop
            if (gameOver) break;
            // else, determine the outcome of the round and then start again.
            determineOutcome();
        }
        // End game session
        System.out.print("Bye!");
    }
}
