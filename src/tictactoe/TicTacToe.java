package tictactoe;

import java.util.Scanner;

public class TicTacToe {
    public static Scanner input = new Scanner(System.in);
    public static char[][] board = new char[][] {{' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}};
    public static boolean gameOver = false;
    public static boolean xMove = true;
    public static char xChar = 'X';
    public static char oChar = 'O';
    public static final int xWins = 264;
    public static final int oWins = 237;
    public static String whoWon;
    public static int turnCounter = 0;

    public static void printBoard (TicTacToe game) {
        System.out.printf("""
                        ---------
                        | %c %c %c |
                        | %c %c %c |
                        | %c %c %c |
                        ---------
                        \n""", board[0][0], board[0][1], board[0][2],
                board[1][0], board[1][1], board[1][2],
                board[2][0], board[2][1], board[2][2]);
    }

    public static void playerMove(TicTacToe game) {
        int validMove = 0;
        while (validMove == 0) {
            if (xMove) System.out.printf("Enter %c coordinates: \n", xChar);
            else System.out.printf("Enter %c coordinates: \n", oChar);
            try {
                int x = input.nextInt() - 1;
                int y = input.nextInt() - 1;
                if (x > 2 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");
                } else if (board[x][y] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                } else {
                    if (xMove) {
                        board[x][y] = 'X';
                        xMove = !xMove;
                    } else {
                        board[x][y] = 'O';
                        xMove = !xMove;
                    }
                    validMove = 1;
                }
            } catch (Exception NumberFormatException) {
                System.out.println("You should enter numbers!");
            }
        }
    }

    public static void checkWinners(TicTacToe game) {
        // define game state
        int[] winningCombinations = new int[] {
                board[0][0] + board[0][1] + board[0][2],
                board[1][0] + board[1][1] + board[1][2],
                board[2][0] + board[2][1] + board[2][2],
                board[0][0] + board[1][1] + board[2][2],
                board[0][2] + board[1][1] + board[2][0],
                board[0][0] + board[1][0] + board[2][0],
                board[0][1] + board[1][1] + board[2][1],
                board[0][2] + board[1][2] + board[2][2]
        };
        for (int i = 0; i < winningCombinations.length; i++) {
            if (winningCombinations[i] == xWins) {
                whoWon = "X wins";
                gameOver = true;
                break;
            } else if (winningCombinations[i] == oWins) {
                whoWon = "O wins";
                gameOver = true;
                break;
            }
        }
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();

        printBoard(game);

        while(!gameOver) {
            turnCounter++;
            if (turnCounter > 9) {
                whoWon = "Draw";
                gameOver = true;
                break;
            }
            playerMove(game);
            printBoard(game);
            checkWinners(game);
        }

        System.out.printf("%s", whoWon);
    }
}