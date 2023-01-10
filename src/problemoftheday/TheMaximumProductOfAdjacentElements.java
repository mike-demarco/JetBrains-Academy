package problemoftheday;//public class problemoftheday.TheMaximumProductOfAdjacentElements {
//}
import java.util.Scanner;

class TheMaximumProductOfAdjacentElements {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int numOfInts = Integer.parseInt(scanner.nextLine());
        int[] boo = new int[numOfInts];
        for (int i = 0; i < numOfInts; i++) {
            boo[i] = scanner.nextInt();
        }
        int maxProd = boo[0] * boo[1];

        for (int i = 2; i < boo.length; i++) {
            if (boo[i - 1] * boo[i] > maxProd) {
                maxProd = boo[i - 1] * boo[i];
            }
        }
        System.out.print(maxProd);
    }
}