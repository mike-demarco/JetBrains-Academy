import java.util.Scanner;

class CountHowManyTimesANumberOccurs {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int[] input = new int[scanner.nextInt()];

        for (int i = 0; i < input.length; i++) {
            input[i] = scanner.nextInt();
        }
        int numberToCount = scanner.nextInt();
        int count = 0;
        scanner.close();
        for (int number: input) {
            if (number == numberToCount) {
                count += 1;
            }
        }
        System.out.print(count);
    }
}