package problemoftheday;
import java.util.Scanner;

class ContainsSubstring {
    public static void main(String[] args) {
        // put your code here
/*        String line = new Scanner(System.in).nextLine();
        if (line.contains("the") && line.contains("The")) {

            System.out.print(Math.min(line.indexOf("the"), line.indexOf("The")));
        } else if (line.contains("the")) {
            System.out.print(line.indexOf("the"));
        } else if (line.contains("The")) {
            System.out.print(line.indexOf("The"));
        } else {
            System.out.print("-1");
        }
*/
        System.out.println(new Scanner(System.in).nextLine().toLowerCase().indexOf("the"));
    }
}
