import java.util.Scanner;

class Triples {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int[] integers = new int[scanner.nextInt()];
        int count = 0;

        int i;
        for (i = 0; i < integers.length; i++) {
            integers[i] = scanner.nextInt();
            if (i >= 2) {
                if (integers[i - 2] == integers[i] - 2 && integers[i - 1] == integers[i] - 1) {
                    count += 1;
                }
            }
        }
        System.out.print(count);
    }
}