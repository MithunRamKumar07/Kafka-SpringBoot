package kafka.example.timeline.kafka;

import kafka.example.timeline.model.TimelineEvent;
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

/** <p> Test class for {@link TimelineProducer }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class TimelineProducerTest {

    @InjectMocks
    TimelineProducer timelineProducer;

    @Mock
    CompletableFuture<SendResult<String, String>> future;

    @Mock
    KafkaTemplate<String,String> timelineKafkaTemplate;

    @Test
    public void testPublish(){
        when(timelineKafkaTemplate.send(any(),any())).thenReturn(future);
        timelineProducer.publishEventToAuthService(new TimelineEvent(),"checkEligibility","AUTH");
    }
}
