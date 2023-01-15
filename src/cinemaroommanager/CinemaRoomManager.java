package cinemaroommanager;

import java.util.Arrays;
import java.util.Scanner;

public class CinemaRoomManager {
    public static int numOfRows = 0;
    public static int numOfSeats = 0;
    public static int totalSeats = numOfSeats * numOfRows;
    public static int[][] cinemaRoomArray;
    public static int numPurchasedTickets = 0;
    public static int profit = 0;
    public static int currentIncome = 0;
    public static int totalIncome = 0;
    public static Scanner scanner = new Scanner(System.in);

    // display menu
    public static void displayMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    // print cinema
    public static void printCinema(CinemaRoomManager cinema) {
        System.out.println("\nCinema:");
        System.out.print("   ");

        for (int i = 0; i < cinemaRoomArray[0].length; i++) {
            System.out.print(i + 1 + " ");
        }

        System.out.println("");

        for (int i = 0; i < cinemaRoomArray.length; i++) {
            if (i < 9) {
                System.out.print(i + 1 + "  ");
            } else {
                System.out.print(i + 1 + " ");
            }
            for (int j = 0; j < cinemaRoomArray[i].length; j++) {
                String seatDistance = "\s".repeat(String.valueOf(j + 1).length());
                if (cinemaRoomArray[i][j] == 1) {
                    System.out.print("B" + seatDistance);
                } else {
                    System.out.print("S" + seatDistance);
                }

            }
            System.out.print("\n");
        }
    }

    // buy a ticket
    public static void buyATicket(CinemaRoomManager cinema) {
        int ticketPrice = 0;
        boolean newTicket = false;
        int rowNumber;
        int seatNumber;

        // set ticket to purchased in array
        do {
            System.out.println("\nEnter a row number:");
            rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            seatNumber = scanner.nextInt();
            if (rowNumber > numOfRows || seatNumber > numOfSeats) {
                System.out.println("Wrong input!");
            } else if (cinemaRoomArray[rowNumber - 1][seatNumber - 1] != 1) {
                numPurchasedTickets++;
                cinemaRoomArray[rowNumber - 1][seatNumber - 1] = 1;
                newTicket = true;
            } else {
                System.out.println("That ticket has already been purchased!\n");
            }
        } while (!newTicket);

        // smaller room
        if (totalSeats <= 60) {
            ticketPrice = 10;
            currentIncome += 10;
        }
        else {
            // larger room
            int frontHalfRows = (numOfRows / 2) + 1;
            if (rowNumber < frontHalfRows) {
                ticketPrice = 10;
                currentIncome += 10;
            }
            else {
                ticketPrice = 8;
                currentIncome += 8;
            }
        }
        System.out.printf("Ticket price: $%d\n", ticketPrice);
    }

    // print statistics
    public static void printStatistics(CinemaRoomManager cinema) {
        System.out.println("\nNumber of purchased tickets: " + numPurchasedTickets);
        float percentageTicketsSold = (float) numPurchasedTickets / (float) totalSeats * 100;
        System.out.format("Percentage: %.2f", percentageTicketsSold);
        System.out.println("%");
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    // main method
    public static void main(String[] args) {
        CinemaRoomManager cinema = new CinemaRoomManager();

        // Write your code here
        // initialize cinema room
        System.out.println("Enter the number of rows:");
        numOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        numOfSeats = scanner.nextInt();
        cinemaRoomArray = new int[numOfRows][numOfSeats];
        totalSeats = numOfRows * numOfSeats;

        // calculate totalIncome
        if (totalSeats <= 60) totalIncome = totalSeats * 10;
        else {
            for (int i = 0; i < numOfRows; i++) {
                for (int j = 0; j < numOfSeats; j++) {
                    if (i < (numOfRows / 2)) totalIncome += 10;
                    else totalIncome += 8;
                }
            }
        }

        // fill cinemaRoomArray with default 0's
        for (int[] ints : cinemaRoomArray) {
            Arrays.fill(ints, 0);
        }

        // run menu loop
        int menuUserOption;

        do {
            displayMenu();
            menuUserOption = scanner.nextInt();
            switch (menuUserOption) {
                case 1 -> printCinema(cinema);
                case 2 -> buyATicket(cinema);
                case 3 -> printStatistics(cinema);
            }
        } while (menuUserOption != 0);
    }
}
