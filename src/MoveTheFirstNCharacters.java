import java.util.Scanner;

class MoveTheFirstNCharacters {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split(" ");

        String word = inputArray[0];
        int countOfChars = Integer.parseInt(inputArray[1]);

        if (countOfChars > word.length() || countOfChars == 0) {
            System.out.print(word);
        } else {
            String substringEnd = word.substring(0, countOfChars);
            String substringFront = word.substring(countOfChars, word.length());

            System.out.print("" + substringFront + substringEnd);
        }

    }
}
