package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String gameInput = scanner.nextLine();
        final int xWins = 264;
        final int oWins = 237;
        int gameState = 0;
        boolean xWon = false;
        boolean oWon = false;
        long xCount = gameInput.chars().filter(ch -> ch == 'X').count();
        long oCount = gameInput.chars().filter(ch -> ch == 'O').count();
        String gameMessage = null;
        int[][] cellArray = new int[][] {
                {0, 0}, {0, 1}, {0, 2},
                {1, 0}, {1, 1}, {1, 2},
                {2, 0}, {2, 1}, {2, 2}
        };

        char[] gameInputArray = new char[] {
                gameInput.charAt(0), gameInput.charAt(1), gameInput.charAt(2),
                gameInput.charAt(3), gameInput.charAt(4), gameInput.charAt(5),
                gameInput.charAt(6), gameInput.charAt(7), gameInput.charAt(8)
        };

        int[] winningCombinations = new int[] {
                gameInputArray[0] + gameInputArray[1] + gameInputArray[2],
                gameInputArray[3] + gameInputArray[4] + gameInputArray[5],
                gameInputArray[6] + gameInputArray[7] + gameInputArray[8],
                gameInputArray[0] + gameInputArray[4] + gameInputArray[8],
                gameInputArray[2] + gameInputArray[4] + gameInputArray[6],
                gameInputArray[0] + gameInputArray[3] + gameInputArray[6],
                gameInputArray[1] + gameInputArray[4] + gameInputArray[7],
                gameInputArray[2] + gameInputArray[5] + gameInputArray[8]
        };

        // print game map
        System.out.printf("""
                        ---------
                        | %c %c %c |
                        | %c %c %c |
                        | %c %c %c |
                        ---------
                        """,
                gameInputArray[0], gameInputArray[1], gameInputArray[2],
                gameInputArray[3], gameInputArray[4], gameInputArray[5],
                gameInputArray[6], gameInputArray[7], gameInputArray[8]);

        // receive input
        int[][] userInput = new int[][] {{scanner.nextInt()}, {scanner.nextInt()}};

        if (userInput == 1, 1) {
            int updatedCell = gameInputArray[0];
        }

        gameInputArray(userInput[0], userInput[1]) = "X";

        System.out.println("You should enter numbers!");
        System.out.println("Coordinates should be from 1 to 3!");

        // define game state
        for (int i = 0; i < winningCombinations.length; i++) {
            if (winningCombinations[i] == xWins) {
                xWon = true;
            } else if (winningCombinations[i] == oWins) {
                oWon = true;
            }
        }

        // impossible checks
        if (xWon && oWon) gameState = 0; // impossible check
        else if (((xCount - oCount) > 1) || ((oCount - xCount) > 1)) gameState = 0; //impossible check
        else if (xWon) gameState = 1; // x wins
        else if (oWon) gameState = 2; // o wins
        else if (gameInput.contains("_")) gameState = 3; // Game not finished check
        else if (!gameInput.contains("_")) gameState = 4; // Draw check


        switch (gameState) {
            case (0):
                // when the grid has three X’s in a row as well as three O’s in a row, or
                // there are a lot more X's than O's or vice versa
                // (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
                gameMessage = "Impossible";
                break;
            case (1):
                // when the grid has three X’s in a row (including diagonals).
                gameMessage = "X wins";
                break;
            case (2):
                // when the grid has three O’s in a row (including diagonals).
                gameMessage = "O wins";
                break;
            case (3):
                // when neither side has three in a row but the grid still has empty cells
                gameMessage = "Game not finished";
                break;
            case (4):
                // when no side has a three in a row and the grid has no empty cells
                gameMessage = "Draw";
                break;
        }
        System.out.println(gameMessage);
    }
}

