package ntnu.fullstack.no.calculatorbackend.util;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.BAD_REQUEST)
public class DivideByZeroException extends RuntimeException {
    public DivideByZeroException(String message) {
      super(message);
    }

    public DivideByZeroException(String message, Throwable cause) {
      super(message, cause);
    }
}
