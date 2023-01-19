package problemoftheday;

import java.util.Scanner;

  class GroundhogsAtAParty {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        // You can use scanner.nextBoolean() to read a boolean value
        byte value = scanner.nextByte();
        boolean value2 = scanner.nextBoolean();

        if (value2 == true) {
            if (15 <= value && value <= 25) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        } else {
            if (10 <= value && value <= 20) {
                System.out.println("true");
            } else {
                System.out.println("false");
            }
        }

    }
}