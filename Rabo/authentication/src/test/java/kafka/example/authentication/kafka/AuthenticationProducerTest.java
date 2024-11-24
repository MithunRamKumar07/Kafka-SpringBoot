package kafka.example.authentication.kafka;


import kafka.example.authentication.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
/** <p> Test class for {@link AuthenticationProducer }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class AuthenticationProducerTest {

    @InjectMocks
    AuthenticationProducer authenticationProducer;

    @Mock
    CompletableFuture<SendResult<String, String>> future;

    @Mock
    KafkaTemplate<String,String> authenticationKafkaTemplate;

    @Test
    public void testPublish(){
        when(authenticationKafkaTemplate.send(any(),any())).thenReturn(future);
        authenticationProducer.publishEventToTimelineService(TestUtils.getTimelineEvent(),
                "authResult","TIMELINE");
    }
}