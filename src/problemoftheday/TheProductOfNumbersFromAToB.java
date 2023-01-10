package problemoftheday;

import java.util.Scanner;

class TheProductOfNumbersFromAToB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int counter;
        long sum = 1;

        for (counter = a; counter < b; counter++) {
            sum *= counter;
        }
        System.out.print(sum);
    }
}