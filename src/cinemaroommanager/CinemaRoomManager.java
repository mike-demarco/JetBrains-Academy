package cinemaroommanager;

import java.util.Arrays;
import java.util.Scanner;

public class CinemaRoomManager {

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numOfSeats = scanner.nextInt();
        int[][] cinemaRoomArray = new int[numOfRows][numOfSeats];

        // print cinema
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i < numOfSeats + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for (int i = 1; i < numOfRows + 1; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < numOfSeats; j++) {
                System.out.print("S ");
            }
            System.out.print("\n");
        }

        System.out.println("\nEnter a row number:");
        int rowNumber = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int seatNumber = scanner.nextInt();

        int ticketPrice = 0;
        // int profit = 0;

        if (numOfSeats * numOfRows <= 60) {
            // small room
            // profit = numOfSeats * numOfRows * 10;
            ticketPrice = 10;
        } else {
            // larger room
            // if (numOfRows % 2 == 0) {
            if (rowNumber < ((numOfRows / 2) + 1)) {
                // profit = numOfSeats * numOfRows / 2 * 10 + numOfSeats * numOfRows / 2 * 8;
                ticketPrice = 10;
            } else {
                // int backHalf = numOfRows / 2 + 1;
                // int frontHalf = numOfRows / 2;
                // profit = numOfSeats * frontHalf * 10 + numOfSeats * backHalf * 8;
                ticketPrice = 8;
            }
        }
        System.out.printf("Ticket price: $%d", ticketPrice);

        // print seat
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int i = 1; i < numOfSeats + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for (int i = 0; i < numOfRows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < numOfSeats; j++) {
                if (i == rowNumber - 1 && j == seatNumber - 1) {
                    System.out.print("B ");
                } else {
                    System.out.print("S ");
                }
            }
            System.out.print("\n");
        }
    }
}