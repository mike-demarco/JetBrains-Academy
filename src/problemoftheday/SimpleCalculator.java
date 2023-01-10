package problemoftheday;

class SimpleCalculator {

    // Implement your methods here
    public static void subtractTwoNumbers(long a, long b) {
        System.out.print(a - b);
    }


    public static void sumTwoNumbers(long a, long b) {
        System.out.print(a + b);
    }


    public static void divideTwoNumbers(long a, long b) {
        if (b == 0) {
            System.out.print("Division by 0!");
        } else {
            System.out.print(a / b);
        }
    }


    public static void multiplyTwoNumbers(long a, long b) {
        System.out.print(a * b);
    }

    // Implemented method
    public static void power(long n, long p) {
        long number = n;
        long power = p;
        long result = 1;
        while (power > 0) {
            if (power % 2 != 0) {
                result *= number;
            }
            power /= 2;
            number *= number;
        }
        System.out.println(result);
    }
}