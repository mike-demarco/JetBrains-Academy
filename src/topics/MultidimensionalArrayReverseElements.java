package topics;

import java.util.Arrays;

public class MultidimensionalArrayReverseElements {
    public static void reverseElements(int[][] twoDimArray) {
        // write your code here
        for (int i = 0; i < twoDimArray.length; i++) {
            for (int j = 0; j < twoDimArray[i].length / 2; j++) {
                int tempElement = twoDimArray[i][j];
                twoDimArray[i][j] = twoDimArray[i][twoDimArray[i].length - 1 - j];
                twoDimArray[i][twoDimArray[i].length - 1 - j] = tempElement;
            }
        }
    }

    public static void printElements(int[][] twoDimArray) {
        for (int i = 0; i < twoDimArray.length; i++) {
            System.out.println(Arrays.toString(twoDimArray[i]));
        }
    }

    public static void main(String[] args) {
        Arrays arrays = null;
        int[][] twoDim = {
                {0, 1, 2},
                {5, 6, 7}
        };
        reverseElements(twoDim);
        printElements(twoDim);
    }
}
