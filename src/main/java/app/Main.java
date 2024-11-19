package app;

import app.model.*;
import app.validator.UserValidator;
import app.repository.LocalRepository;
import app.service.RegistrationService;

public class Main {

    public static void main(String[] args) {

        LocalRepository repository = new LocalRepository();
        UserValidator validator = new UserValidator();

        RegistrationService registrationService = new RegistrationService(repository, validator);

        User user1 = new User("1", "Den", "dew@gmail.com", UserRole.ADMIN);
        User user2 = new User("2", "Max", "anc@gmail.com", UserRole.REGULAR);

        System.out.println("Registration users...");
        registrationService.registerUser(user1);
        registrationService.registerUser(user2);

        System.out.println("Find all users...");
        registrationService.getAllUsers().forEach(System.out::println);

        System.out.println("Find user by ID");
        registrationService.getUserById("1").ifPresentOrElse(
                System.out::println,
                () -> System.out.println("User not found!")
        );

        System.out.println("Update user 2 to ADMIN");
        User newUser2 = new User("2", "Max", "anc@gmail.com", UserRole.ADMIN);

        try {
            registrationService.updateUser(newUser2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (registrationService.deleteUser("1")) {
            System.out.println("User with id 1 deleted");
        } else {
            System.out.println("User with id 1 not found!");
        }

        System.out.println("Find all users...");
        registrationService.getAllUsers().forEach(System.out::println);

    }
}
