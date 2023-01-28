package topics;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindTheGreatestNumberInAFile {
    public static void main(String[] args) {
        File file = new File("documents/dataset_91007.txt"); // a file on Windows

        double maxNumber = 0;
        
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                double index = scanner.nextDouble();
                if (index > maxNumber) maxNumber = index;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + file.getAbsolutePath());
        }
        System.out.print(maxNumber);
    }
}
