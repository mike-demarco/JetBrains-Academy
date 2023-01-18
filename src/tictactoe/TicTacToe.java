package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {

    // print game
    public static void printGameGrid(String gameInput) {
        System.out.printf("""
                        ---------
                        | %c %c %c |
                        | %c %c %c |
                        | %c %c %c |
                        ---------
                        """, gameInput.charAt(0), gameInput.charAt(1), gameInput.charAt(2),
                gameInput.charAt(3), gameInput.charAt(4), gameInput.charAt(5),
                gameInput.charAt(6), gameInput.charAt(7), gameInput.charAt(8));
    }


    // run game state
    public static void printGameState(String gameInput) {
        int gameState = 0;
        List<String> XWinners = new ArrayList<>(List.of(
                "XXX*", "???XXX???", "*XXX",
                "X???X???X", "??X?X?X*",
                "X??X??X*", "?X??X??X?", "??X??X??X"));

        List<String> OWinners = new ArrayList<>(List.of(
                "OOO*", "???OOO???", "*OOO",
                "O???O???O", "??O?O?O*",
                "O??O??O*", "?O??O??O?", "??O??O??O"));

        for (int i = 0; i < XWinners.toArray().length; i++) {
            for (int j = 0; j < OWinners.toArray().length; j++) {
                if (gameInput.contains(XWinners.get(i)) && gameInput.contains(OWinners.get(j))) {
                    gameState = 4;
                }
            }
            if (gameInput.contains(XWinners.get(i))) {
                gameState = 2;
            } else if (gameInput.contains(OWinners.get(i))) {
                gameState = 3;
            } else if (!gameInput.contains(" ") && !gameInput.contains("_")) {
                gameState = 1;
            } else if (gameInput.contains(" ") || gameInput.contains("_")) {
                gameState = 0;
            }  {
                gameState = 4;
            }
        }

        switch (gameState) {
            // when neither side has three in a row but the grid still has empty cells
            case (0):
                System.out.println("Game not finished");
                break;
            case (1):
                // when no side has a three in a row and the grid has no empty cells
                System.out.println("Draw");
                break;
            case (2):
                // when the grid has three X’s in a row (including diagonals).
                System.out.println("X wins");
                break;
            case (3):
                // when the grid has three O’s in a row (including diagonals).
                System.out.println("O wins");
                break;
            case (4):
                // when the grid has three X’s in a row as well as three O’s in a row, or
                // there are a lot more X's than O's or vice versa
                // (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
                System.out.println("Impossible");
                break;
        }
    }

    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gameInput = new String(scanner.nextLine());

        printGameGrid(gameInput);
        printGameState(gameInput);
    }
}

