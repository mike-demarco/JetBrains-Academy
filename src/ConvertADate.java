import java.util.Scanner;

class ConvertADate {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] inputArray = scanner.nextLine().split("-");
        System.out.print("" + inputArray[1] + "/" + inputArray[2] + "/" + inputArray[0]);
    }
}
