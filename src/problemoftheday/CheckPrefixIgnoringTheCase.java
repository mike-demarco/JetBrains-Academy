package problemoftheday;

import java.util.Scanner;

class CheckPrefixIgnoringTheCase {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        // declare character variable and initialize to scanner object with charAt(0) method
        char first = scanner.next().charAt(0);
        // declare boolean variable and initialize to test of character object for 'J' or 'j'
        boolean result = first == 'J' || first == 'j';
        // close scanner
        scanner.close();
        // print out boolean variable
        System.out.println(result);
    }
}