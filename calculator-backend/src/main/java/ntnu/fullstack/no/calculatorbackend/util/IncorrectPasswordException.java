package ntnu.fullstack.no.calculatorbackend.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class IncorrectPasswordException extends RuntimeException {
    public IncorrectPasswordException(String message) {
      super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
      super(message, cause);
    }
}
