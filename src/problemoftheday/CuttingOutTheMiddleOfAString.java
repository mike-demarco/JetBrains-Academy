package problemoftheday;

import java.util.Scanner;

class CuttingOutTheMiddleOfAString {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        int wordLength = inputString.length();
        String outputStringBeginning;
        String outputStringEnd;

        if (wordLength % 2 == 0) {
            outputStringBeginning = inputString.substring(0, wordLength / 2 - 1);
            outputStringEnd = inputString.substring(wordLength / 2 + 1, wordLength);
        } else {
            outputStringBeginning = inputString.substring(0, wordLength / 2);
            outputStringEnd = inputString.substring(wordLength / 2 + 1, wordLength);
        }
        System.out.print("" + outputStringBeginning + outputStringEnd);
    }
}
