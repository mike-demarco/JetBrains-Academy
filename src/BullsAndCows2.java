import java.util.Scanner;

public class BullsAndCows2 {

    /*
    System.out.println(getNthDigit(123, 10, 1));  // 3
    System.out.println(getNthDigit(123, 10, 2));  // 2
    System.out.println(getNthDigit(123, 10, 3));  // 1
     */
    public int getNthDigit(int number, int base, int n) {
        return (int) ((number / Math.pow(base, n - 1)) % base);
    }

    public static void main (String[] args) {
        BullsAndCows2 bull = new BullsAndCows2();

        Scanner scanner = new Scanner(System.in);
        int secretCodeGuess = scanner.nextInt();

        int min = 1000;
        int max = 9999;
        int secretCode = (int)(Math.random()*(max-min+1)+min);
        int secretCodeDigit1 = bull.getNthDigit(secretCode, 10, 1);
        int secretCodeDigit2 = bull.getNthDigit(secretCode, 10, 2);
        int secretCodeDigit3 = bull.getNthDigit(secretCode, 10, 3);
        int secretCodeDigit4 = bull.getNthDigit(secretCode, 10, 4);

        int countOfBulls = 0;
        int countOfCows = 0;

        int moduleCounter = 1;
        while (moduleCounter < 5) {
            int secretCodeGuessDigit = bull.getNthDigit(secretCodeGuess, 10, moduleCounter);
            int secretCodeDigit = bull.getNthDigit(secretCode, 10, moduleCounter);

            if (secretCodeGuessDigit == secretCodeDigit) {
                countOfBulls++;
            } else if (secretCodeGuessDigit == secretCodeDigit1 ||
                    secretCodeGuessDigit == secretCodeDigit2 ||
                    secretCodeGuessDigit == secretCodeDigit3 ||
                    secretCodeGuessDigit == secretCodeDigit4) {
                countOfCows++;
            }
            moduleCounter += 1;
        }

        if (countOfBulls > 0 && countOfCows > 0) {
            System.out.print(String.format("Grade: %d bull(s) and %d cow(s). The secret code is %s.",
                    countOfBulls, countOfCows, secretCode));
        } else if (countOfBulls == 0 && countOfCows > 0) {
            System.out.print(String.format("Grade: %d cow(s). The secret code is %s.", countOfCows, secretCode));
        } else if (countOfBulls > 0 && countOfCows == 0) {
            System.out.print(String.format("Grade: %d bull(s). The secret code is %s.", countOfBulls, secretCode));
        } else {
            System.out.print(String.format("Grade: None. The secret code is %s.", secretCode));
        }
    }
}
