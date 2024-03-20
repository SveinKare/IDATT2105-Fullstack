package ntnu.fullstack.no.calculatorbackend.controller;

import jakarta.validation.Valid;
import ntnu.fullstack.no.calculatorbackend.model.User;
import ntnu.fullstack.no.calculatorbackend.service.UserService;
import ntnu.fullstack.no.calculatorbackend.util.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/user")
public class UserController {
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  ResponseEntity<Boolean> registerUser(@Valid @RequestBody User user) {
    userService.registerUser(user);
    return ResponseEntity.ok(null);
  }

  @PostMapping("/login")
  ResponseEntity<ErrorResponse> loginUser(@Valid @RequestBody User user) {
    userService.validateUser(user);
    return ResponseEntity.ok(null);
  }
}
