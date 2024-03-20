package ntnu.fullstack.no.calculatorbackend.controller;

import ntnu.fullstack.no.calculatorbackend.model.ExpressionEntry;
import ntnu.fullstack.no.calculatorbackend.model.User;
import ntnu.fullstack.no.calculatorbackend.service.CalculatorService;
import ntnu.fullstack.no.calculatorbackend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class CalculatorController {
  private final Logger logger = LoggerFactory.getLogger(CalculatorController.class);
  private final CalculatorService calcService;
  private final UserService userService;

  @Autowired
  public CalculatorController(CalculatorService calcService, UserService userService) {
    this.calcService = calcService;
    this.userService = userService;
  }

  @PostMapping("/evaluate")
  public ResponseEntity<String> evaluate(@RequestBody ExpressionEntry body) {
    userService.findUser(body.getUsername()); //Throws exception if user does not exist
    logger.info(String.format("New expression: %s\nFrom user: %s",body.getExpression(), body.getUsername()));
    String result = calcService.evaluateExpression(body);
    return ResponseEntity.ok(result);
  }

  @PostMapping("/history")
  public ResponseEntity<String[]> getHistory(@RequestBody User user) {
    userService.findUser(user.getUsername()); //Throws exception if user does not exist
    return ResponseEntity.ok(calcService.getHistory(user.getUsername()));
  }
}
