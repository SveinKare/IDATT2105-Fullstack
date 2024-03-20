package ntnu.fullstack.no.calculatorbackend.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String message) {
      super(message);
    }

    public UserAlreadyRegisteredException(String message, Throwable cause) {
      super(message, cause);
    }
}
