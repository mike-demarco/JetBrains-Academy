package battleship;
import amazingnumbers.AmazingNumbers;
import cinemaroommanager.CinemaRoomManager;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Battleship {
    static int gameBoardLength = 12;
    static char waterChar = '~';
    static char shipChar = '0';
    static boolean gameOver = false;


    static String[][] validStringArray = new String[][] {
            {"A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10"},
            {"B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "B10"},
            {"C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10"},
            {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10"},
            {"E1", "E2", "E3", "E4", "E5", "E6", "E7", "E8", "E9", "E10"},
            {"F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10"},
            {"G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "G10"},
            {"H1", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10"},
            {"I1", "I2", "I3", "I4", "I5", "I6", "I7", "I8", "I9", "I10"},
            {"J1", "J2", "J3", "J4", "J5", "J6", "J7", "J8", "J9", "J10"}
    };

    public static boolean checkCoordinatesValidity(String beginningOfShip, String endOfShip, String typeOfShip) {
        try {
            // if valid inputs (length of chars == 2)
            if (beginningOfShip.length() == 2 && endOfShip.length() == 2) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {


                        //  (A1 - J10)
                        if (validStringArray[i][j].equalsIgnoreCase(beginningOfShip)) {
                            switch (typeOfShip) {
                                case "Aircraft Carrier" -> {
                                    for (int k = j; k < 5; k++) {
                                        // if not overlapping with ships that are on the board with one space buffer
                                        // if file[i + 1][j] == 0 or file[i][j + 1] == 0
                                        // System.out.println("Error! You placed it too close to another one. Try again:")

                                        if (validStringArray[i][j + 5].equalsIgnoreCase(endOfShip) ||
                                                validStringArray[i + 5][j].equalsIgnoreCase(endOfShip)) {
                                            return true;
                                        } else {
                                            System.out.println("Error! Wrong ship location! Try again:");
                                        }
                                    }
                                }
                                case "Battleship" -> {
                                    if (validStringArray[i][j + 4].equalsIgnoreCase(endOfShip) ||
                                            validStringArray[i + 4][j].equalsIgnoreCase(endOfShip)) {
                                        return true;
                                    }
                                }
                                case "Submarine", "Cruiser" -> {
                                    if (validStringArray[i][j + 3].equalsIgnoreCase(endOfShip) ||
                                            validStringArray[i + 3][j].equalsIgnoreCase(endOfShip)) {
                                        return true;
                                    }
                                }
                                case "Destroyer" -> {
                                    if (validStringArray[i][j + 2].equalsIgnoreCase(endOfShip) ||
                                            validStringArray[i + 2][j].equalsIgnoreCase(endOfShip)) {
                                        return true;
                                    }
                                }
                            }
                        }
                    }
                }
            }


        } catch (IndexOutOfBoundsException e) {
            System.out.print("Error! Wrong length of the Aircraft Carrier! Try again:\n");
            return false;
        }
    }

    public static void placeShip(String beginningOfShip, String endOfShip) {
        // fill board with O for each position between beginningOfShip and endOfShip
        // open file, write O's to board between beginning and end of ship, close file
        for (int[] ints : cinemaRoomArray) {
            Arrays.fill(ints, 0);
        }
    }

    public static void main(String[] args) {
        char[][] gameBoard = createGameBoard();
        printGameBoard();

        Scanner scanner = new Scanner(System.in);

        String aircraftCarrierCoordinates = null,
                battleshipCoodinates = null,
                submarineCoordinates = null,
                cruiserCoordinates = null,
                destroyerCoordinates = null,
                beginningOfShip = null,
                endOfShip = null;
        String[] inputString = null;

        while(!gameOver) {
            try {
                System.out.print("Enter the coordinates of the Aircraft Carrier (5 cells):\n");
                String typeOfShip = "Aircraft Carrier";
                while (aircraftCarrierCoordinates == null) {
                    inputString = scanner.nextLine().split(" ");
                    if (inputString.length == 2) {
                        beginningOfShip = inputString[0];
                        endOfShip = inputString[1];
                    } else {
                        System.out.print("Error! Wrong length of the Aircraft Carrier! Try again:\n");
                        continue;
                    }
                    if (checkCoordinatesValidity(beginningOfShip, endOfShip, typeOfShip)) {

                    }
                }
            } catch (InputMismatchException e) {

            }
        }
    }

    private static void printGameBoard(char[][] gameBoard) {
        // print cinema
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char rowChar = '\u0030';

        for (int i = 0; i < gameBoard[0].length; i++) {
            System.out.print(rowChar);
            rowChar++;
            for (int j = 0; i < gameBoard[0].length; j++) {
                System.out.print(" " + gameBoard[i][j]);
            }
        }
    }

    private static char[][] createGameBoard() {
        char[][] gameBoard = new char[gameBoardLength][gameBoardLength];
        for (char[] row : gameBoard) {
            Arrays.fill(row, waterChar);
        }
    }
}

