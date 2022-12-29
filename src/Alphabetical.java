import java.util.Scanner;

class Alphabetical {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] array = scanner.nextLine().split(" ");
        boolean check = true;
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                continue;
            }
            int isAlphabetical = array[i].compareTo(array[i - 1]);
            if (isAlphabetical >= 0) {
                continue;
            } else if (isAlphabetical < 0) {
                check = false;
                break;
            }
        }
        System.out.print(check);
    }
}