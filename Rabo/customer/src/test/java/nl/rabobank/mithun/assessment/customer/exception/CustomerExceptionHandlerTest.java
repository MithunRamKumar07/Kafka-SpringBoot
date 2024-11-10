package nl.rabobank.mithun.assessment.customer.exception;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** <p> Test class for {@link CustomerExceptionHandler }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerExceptionHandlerTest {

    @InjectMocks
    CustomerExceptionHandler customerExceptionHandler;

    @Test
    public void testCustomerException(){
        CustomerException recipeException = new CustomerException("Exception");
        ResponseEntity<ErrorResponse> response = customerExceptionHandler.handleAuthenticationException(recipeException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testException(){
        ResponseEntity<ErrorResponse> response = customerExceptionHandler.handleException(new Exception());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR,response.getStatusCode());
    }
}