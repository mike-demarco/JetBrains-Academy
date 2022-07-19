import java.util.Scanner;

class ReplacingAWithB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        String input = scanner.next();
        System.out.print(input.replace('a', 'b'));
    }
}