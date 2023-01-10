import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        // x = (-b ± sqrt(b^2 - 4ac)) / 2a
        double x1 = (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);
        double x2 = (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / (2 * a);

        double max = Math.max(x1, x2);

        if (max == x1) {
            System.out.print(x2 + " " + x1);
        } else System.out.print(x1 + " " + x2);
    }
}
