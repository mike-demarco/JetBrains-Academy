import java.util.*;

public class Boxes {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        int[] box1 = new int[3];
        int[] box2 = new int[3];
        int counterA = 0;
        int counterB = 0;
        for (int i = 0; i < box1.length; i++) {
            box1[i] = scanner.nextInt();
        }
        Arrays.sort(box1);
        for (int i = 0; i < box2.length; i++) {
            box2[i] = scanner.nextInt();
        }
        Arrays.sort(box2);

        for (int i = 0; i < box1.length; i++) {
            if (box1[i] < box2[i]) {
                counterA += 1;
            } else if (box1[i] > box2[i]) {
                counterB += 1;
            }
        }

        if (counterA == 3) {
            System.out.print("Box 1 < Box 2");
        } else if (counterB == 3) {
            System.out.print("Box 1 > Box 2");
        } else System.out.print("Incompatible");
    }
}