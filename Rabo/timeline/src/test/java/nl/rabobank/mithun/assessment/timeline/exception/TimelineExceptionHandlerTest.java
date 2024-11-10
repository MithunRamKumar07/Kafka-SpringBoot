package nl.rabobank.mithun.assessment.timeline.exception;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/** <p> Test class for {@link TimelineExceptionHandler }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class TimelineExceptionHandlerTest {

    @InjectMocks
    TimelineExceptionHandler timelineExceptionHandler;

    @Test
    public void testCustomerException() {
        TimelineException recipeException = new TimelineException("Exception");
        ResponseEntity<ErrorResponse> response = timelineExceptionHandler.handleAuthenticationException(recipeException);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testException() {
        ResponseEntity<ErrorResponse> response = timelineExceptionHandler.handleException(new Exception());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}