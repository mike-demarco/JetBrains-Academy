package problemoftheday;

import java.util.Scanner;
import java.lang.StringBuilder;

class ConcatAllStringsWithoutDigits {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here
        StringBuilder resultString = new StringBuilder();

        // for each string
        for (int i = 0; i < strings.length; i++) {
            // for length of string
            StringBuilder sb = new StringBuilder(strings[i]);
            for (int j = 0; j < sb.length(); j++) {
                // if charAt index
                if (!((sb.charAt(j) > 64 && sb.charAt(j) < 91) || (sb.charAt(j) > 96 && sb.charAt(j) < 123))) {
                    // delete char
                    sb.deleteCharAt(j);
                    j--;
                }
            }
            // concat string to result
            resultString.append(sb);
        }
        return resultString.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split("\\s+");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}