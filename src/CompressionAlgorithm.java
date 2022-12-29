import java.util.Scanner;

class CompressionAlgorithm {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String tempString = scanner.nextLine();
        int len = tempString.length();
        String ans = "";

        // Traverse the String
        for (int i = 0; i + 1 <= len; ) {
            // Add previously unencountered char to ans string
            char tempChar = tempString.charAt(i);
            ans = ans + tempChar;
            int j = 1;

            // Next char
            i++;
            if (i == len) {
                ans = ans + j;
                break;
            } else {
                char currentChar = tempString.charAt(i);
                while (currentChar == tempChar) {
                    j++;
                    if (i + 1 == len) {
                        i++;
                        break;
                    }
                    i++;
                    currentChar = tempString.charAt(i);
                }
                ans = ans + j;
            }
        }
        System.out.print(ans);
    }
}
