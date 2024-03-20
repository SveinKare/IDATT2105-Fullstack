package ntnu.fullstack.no.calculatorbackend.util;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse(
        "User not found",
        HttpStatus.NOT_FOUND.value(),
        LocalDateTime.now().toString());
    return new ResponseEntity<>(errorResponse, null, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(IncorrectPasswordException.class)
  public ResponseEntity<ErrorResponse> handleIncorrectPasswordException(IncorrectPasswordException e) {
    ErrorResponse errorResponse = new ErrorResponse(
        "Incorrect password",
        HttpStatus.UNAUTHORIZED.value(),
        LocalDateTime.now().toString());
    return new ResponseEntity<>(errorResponse, null, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UserAlreadyRegisteredException.class)
  public ResponseEntity<ErrorResponse> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {
    ErrorResponse errorResponse = new ErrorResponse(
        "User already registered",
        HttpStatus.FORBIDDEN.value(),
        LocalDateTime.now().toString());
    return new ResponseEntity<>(errorResponse, null, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(DivideByZeroException.class)
  public ResponseEntity<ErrorResponse> handleDivideByZeroException(DivideByZeroException e) {
    ErrorResponse errorResponse = new ErrorResponse(
        "Division by zero is not allowed",
        HttpStatus.BAD_REQUEST.value(),
        LocalDateTime.now().toString());
    return new ResponseEntity<>(errorResponse, null, HttpStatus.BAD_REQUEST);
  }
}
