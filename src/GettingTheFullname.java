
class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        // write your code here
        if (firstName != null) {
            this.firstName = firstName;
        } else {
            this.firstName = null;
        }
    }

    public void setLastName(String lastName) {
        // write your code here
        if (lastName != null) {
            this.lastName = lastName;
        } else {
            this.lastName = null;
        }
    }

    public String getFullName() {
        String unknown = "Unknown";
        if (firstName != null && lastName != null) {
            return firstName + " " + lastName;
        } else if (firstName == null && lastName != null) {
            return lastName;
        } else if (firstName != null && lastName == null) {
            return firstName;
        } else if (firstName == null && lastName == null) {
            return unknown;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        User tim = new User();
        tim.setFirstName(null);
        tim.setLastName(null);
        System.out.println(tim.getFullName()); // Tim Towler
    }
}