package problemoftheday;

import java.util.Scanner;

class TheLuckyTicket {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        char[] inputCharArray = scanner.nextLine().toCharArray();
        int size = inputCharArray.length;
        int[] inputIntArray = new int[size];
        for (int i = 0; i < size; i++) {
            inputIntArray[i] = Character.getNumericValue(inputCharArray[i]);
        }
        String check = "Lucky";
        int firstSetSum = inputIntArray[0] + inputIntArray[1] + inputIntArray[2];
        int lastSetSum = inputIntArray[size - 1] + inputIntArray[size - 2] + inputIntArray[size - 3];

        if (firstSetSum == lastSetSum) {
            check = "Lucky";
        } else {
            check = "Regular";
        }
        System.out.print(check);
    }
}
