import java.util.Scanner;

public class RaiseToThePower {

    public static long power(int n, int m) {
        // write your code here
        long count = 1;
        for (int i = 0; i < m; i++) {
            count *= n;
        }
        return count;
    }

    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int n = scanner.nextInt();
        final int m = scanner.nextInt();
        System.out.println(power(n, m));
    }
}