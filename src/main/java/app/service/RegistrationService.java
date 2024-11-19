package app.service;

import app.model.User;
import app.validator.*;
import app.repository.*;

import java.util.List;
import java.util.Optional;

public class RegistrationService {

    private final Repository<User> userRepository;
    private final Validator<User> validator;

    public RegistrationService(Repository<User> userRepository, Validator<User> validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    public boolean registerUser(User user) {
        if (validator.isValid(user)) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean updateUser(User user) {
        if (validator.isValid(user)){
            ((LocalRepository) userRepository).update(user);
            return true;
        }
        throw new IllegalArgumentException("User with id " + user.getId() + " not found!");
    }

    public boolean deleteUser (String id){
        if (userRepository.findById(id).isPresent()){
            ((LocalRepository) userRepository).delete(id);
            return true;
        }
        return false;
    }
}
