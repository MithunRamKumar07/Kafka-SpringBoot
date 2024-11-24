package kafka.example.authentication.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**<p> Exception Handler for {@link AuthenticationException} and other exceptions that occur in Auth Service</p>
 */
@ControllerAdvice
@Component
@Slf4j
public class AuthenticationExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public final ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException authenticationException){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(frameErrorResponse(authenticationException.getExceptionMessage()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleException(Exception exception){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(frameErrorResponse(exception.getMessage()));
    }

    private ErrorResponse frameErrorResponse(String errorDetails) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorDetails(errorDetails);
        return errorResponse;
    }
}
