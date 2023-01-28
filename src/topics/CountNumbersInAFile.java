package topics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CountNumbersInAFile {
    public static void main(String[] args) {
        File file = new File("documents/dataset_91022.txt"); // a file on Windows

        int count = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                double index = scanner.nextInt();
                if (index >= 9999) count++;

            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file.getAbsolutePath());
        }
        System.out.print(count);
    }
}
