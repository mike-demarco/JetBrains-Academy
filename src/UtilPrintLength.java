class Util {
    // correct this method to avoid NPE
    public static void printLength(String name) {

        // Checking if name.equals null or works fine.
        try {
            // This line of code throws NullPointerException
            // because name is null
            System.out.println(name.length());
        } catch (NullPointerException e) {
            System.out.print("NullPointerException Caught");
        }
    }
}