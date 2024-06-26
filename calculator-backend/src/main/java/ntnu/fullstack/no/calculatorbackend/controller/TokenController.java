package ntnu.fullstack.no.calculatorbackend.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import java.time.Duration;
import java.time.Instant;
import ntnu.fullstack.no.calculatorbackend.model.User;
import ntnu.fullstack.no.calculatorbackend.service.UserService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
@RequestMapping("/token")
public class TokenController {
  public static final String SECRET_TEST = "testsecrettestsecrettestsecrettestsecrettestsecret";
  private final UserService userService;
  private static final Duration JWT_TOKEN_VALIDITY = Duration.ofMinutes(5);

  public TokenController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("")
  public ResponseEntity<String> generateToken(final @RequestBody User loginRequest) {
    // if username and password are valid, issue an access token
    // note that subsequent requests need this token
    userService.validateUser(loginRequest);
    return ResponseEntity.ok(generateToken(loginRequest.getUsername()));
  }

  public String generateToken(final String userId) {
    final Instant now = Instant.now();
    final Algorithm hmac512 = Algorithm.HMAC512(SECRET_TEST);
    final JWTVerifier verifier = JWT.require(hmac512).build();
    return JWT.create()
        .withSubject(userId)
        .withIssuer("idatt2105_token_issuer_app")
        .withIssuedAt(now)
        .withExpiresAt(now.plusMillis(JWT_TOKEN_VALIDITY.toMillis()))
        .sign(hmac512);
  }

}
