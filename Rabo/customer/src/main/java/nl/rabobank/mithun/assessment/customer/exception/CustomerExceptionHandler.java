package nl.rabobank.mithun.assessment.customer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/**<p> Exception Handler for {@link CustomerException} and other exceptions that occur in Customer Service</p>
 */
@ControllerAdvice
@Component
@Slf4j
public class CustomerExceptionHandler {

    @ExceptionHandler(CustomerException.class)
    public final ResponseEntity<ErrorResponse> handleAuthenticationException(CustomerException authenticationException){
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
