package kafka.example.authentication.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** <p> Test class for {@link AuthenticationExceptionHandler }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationExceptionHandlerTest {

    @InjectMocks
    AuthenticationExceptionHandler authenticationExceptionHandler;

    @Test
    public void testCustomerException(){
        AuthenticationException authenticationException = new AuthenticationException("Exception");
        ResponseEntity<ErrorResponse> response = authenticationExceptionHandler.handleAuthenticationException(authenticationException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testException(){
        ResponseEntity<ErrorResponse> response = authenticationExceptionHandler.handleException(new Exception());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}