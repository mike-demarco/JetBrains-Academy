package topics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TheSumOfNumbersInAFile {
    public static void main(String[] args) {
        File file = new File("documents/dataset_91033.txt"); // a file on Windows

        double sum = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                sum += scanner.nextInt();
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file.getAbsolutePath());
        }
        System.out.print(sum);
    }
}
