package problemoftheday;

import java.util.Scanner;

class ExactlyOneParameterIsPositive {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        Byte value = scanner.nextByte();
        Byte value2 = scanner.nextByte();
        Byte value3 = scanner.nextByte();
        boolean truth = ((value > 0) && !(value2 > 0) && !(value3 > 0) ||
                ((value > 0) && !(value2 > 0) && !(value3 > 0) ||
                        ((value > 0) && !(value2 > 0) && !(value3 > 0))));

        // if value

    }
}