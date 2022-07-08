import java.util.Scanner;

class FizzBuzz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int inputMin = scanner.nextInt();
        int inputMax = scanner.nextInt();
        final int three = 3;
        final int five = 5;

        int i;
        for (i = inputMin; i < inputMax + 1; i++) {
            if (i % three == 0 && i % five == 0) {
                System.out.println("FizzBuzz");
            } else if (i % three == 0) {
                System.out.println("Fizz");
            } else if (i % five == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}