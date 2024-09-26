class User {
    // Mandatory fields
    private String firstName;
    private String lastName;
    private String email;

    // Optional fields
    private String phoneNumber;
    private String address;
    private String jobTitle;

    // Private constructor to force the use of the builder
    private User(UserBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.address = builder.address;
        this.jobTitle = builder.jobTitle;
    }

    @Override
    public String toString() {
        return "User [firstName=" + firstName + ", lastName=" + lastName + 
               ", email=" + email + ", phoneNumber=" + phoneNumber + 
               ", address=" + address + ", jobTitle=" + jobTitle + "]";
    }

    // Step 2: Create the interfaces to enforce setting mandatory fields in sequence

    public interface FirstNameStep {
        LastNameStep withFirstName(String firstName);
    }

    public interface LastNameStep {
        EmailStep withLastName(String lastName);
    }

    public interface EmailStep {
        OptionalStep withEmail(String email);
    }

    // Optional fields interface allows method chaining
    public interface OptionalStep {
        OptionalStep withPhoneNumber(String phoneNumber);
        OptionalStep withAddress(String address);
        OptionalStep withJobTitle(String jobTitle);
        User build();
    }

    // Step 3: Implement the UserBuilder to enforce the steps
    public static class UserBuilder implements FirstNameStep, LastNameStep, EmailStep, OptionalStep {
        // Mandatory fields
        private String firstName;
        private String lastName;
        private String email;

        // Optional fields
        private String phoneNumber;
        private String address;
        private String jobTitle;

        // The builder starts at the first mandatory step
        private UserBuilder() {}

        // Method to initiate the builder
        public static FirstNameStep builder() {
            return new UserBuilder();
        }

        // Step-by-step mandatory field setters
        @Override
        public LastNameStep withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        @Override
        public EmailStep withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        @Override
        public OptionalStep withEmail(String email) {
            this.email = email;
            return this;
        }

        // Optional field setters with method chaining
        @Override
        public OptionalStep withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        @Override
        public OptionalStep withAddress(String address) {
            this.address = address;
            return this;
        }

        @Override
        public OptionalStep withJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        // Final build method to return the fully built User object
        @Override
        public User build() {
            return new User(this);
        }
    }
}

public class BuilderPatternExample {
    public static void main(String[] args) {
        // Create a User with all mandatory and some optional fields
        User user1 = User.UserBuilder.builder()
                            .withFirstName("John")
                            .withLastName("Doe")
                            .withEmail("john.doe@example.com")
                            .withPhoneNumber("123-456-7890")
                            .withJobTitle("Software Engineer")
                            .build();

        System.out.println(user1);

        // Create another User with only mandatory fields
        User user2 = User.UserBuilder.builder()
                            .withFirstName("Jane")
                            .withLastName("Smith")
                            .withEmail("jane.smith@example.com")
                            .build();

        System.out.println(user2);
    }
}
