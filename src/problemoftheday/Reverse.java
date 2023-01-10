package problemoftheday;

import java.util.Scanner;

class Reverse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        int input = scanner.nextInt();
        int a = input % 1000 / 100;
        int b = input % 100 / 10;
        int c = input % 10;
        if (c != 0) {
            System.out.print(c);
        }
        System.out.print(b);
        System.out.print(a);
    }
}