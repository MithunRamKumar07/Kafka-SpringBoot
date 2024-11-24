package kafka.example.timeline.kafka;

import kafka.example.timeline.model.TimelineEvent;
import kafka.example.timeline.repository.MessageRepository;
import kafka.example.timeline.repository.TimelineRepository;
import kafka.example.timeline.service.TimelineService;
import kafka.example.timeline.util.TimelineUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** <p> Test class for {@link TimelineConsumer }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class TimelineConsumerTest {

    @InjectMocks
    TimelineConsumer timelineConsumer;

    @Mock
    TimelineService timelineService;

    @Mock
    TimelineRepository timelineRepository;

    @Mock
    MessageRepository messageRepository;

    @Test
    void testConsumeEventsWithActiveMembership(){
        timelineConsumer.listenAuthEvents(TimelineUtils.getStringFromObject(new TimelineEvent(1,1,"message","authResult",true)));
    }

    @Test
    void testConsumeEventsWithInactiveMembership(){
        timelineConsumer.listenAuthEvents(TimelineUtils.getStringFromObject(new TimelineEvent(1,1,"message","authResult",false)));
    }

    @Test
    void testDLT(){
        timelineConsumer.listenToDeadLetterQueue();
    }
}
