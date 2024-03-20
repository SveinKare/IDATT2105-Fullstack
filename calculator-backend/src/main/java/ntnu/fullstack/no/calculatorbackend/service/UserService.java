package ntnu.fullstack.no.calculatorbackend.service;

import ntnu.fullstack.no.calculatorbackend.util.IncorrectPasswordException;
import ntnu.fullstack.no.calculatorbackend.util.UserAlreadyRegisteredException;
import ntnu.fullstack.no.calculatorbackend.util.UserNotFoundException;
import ntnu.fullstack.no.calculatorbackend.model.User;
import ntnu.fullstack.no.calculatorbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void registerUser(User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new UserAlreadyRegisteredException("That user is already registered.");
    }
    userRepository.save(user);
  }

  public void validateUser(User user) {
    User found = findUser(user.getUsername());
    if (!found.getPassword().equals(user.getPassword())) {
      throw new IncorrectPasswordException("Incorrect password.");
    }
  }

  /**
   * Finds the given user in the database.
   *
   * @param username (String) The username to find.
   * @return The user if it exists, otherwise an exception is thrown.
   * @throws UserNotFoundException If the user does not exist.
   */
  public User findUser(String username) {
    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new UserNotFoundException("That user does not exist."));
  }
}
