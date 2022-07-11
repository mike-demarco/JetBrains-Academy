import java.util.Scanner;

class TheSumOfArrayElements {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int[] length = new int[scanner.nextInt()];
        int sum = 0;
        for (int element: length) {
            element = scanner.nextInt();
            sum += element;
        }
        System.out.print(sum);
    }
}