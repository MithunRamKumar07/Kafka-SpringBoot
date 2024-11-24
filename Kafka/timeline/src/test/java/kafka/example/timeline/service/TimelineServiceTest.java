package kafka.example.timeline.service;

import kafka.example.timeline.kafka.TimelineProducer;
import kafka.example.timeline.model.Message;
import kafka.example.timeline.model.Timeline;
import kafka.example.timeline.model.TimelineEvent;
import kafka.example.timeline.repository.MessageRepository;
import kafka.example.timeline.repository.TimelineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** <p> Test class for {@link TimelineService }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class TimelineServiceTest {

    @InjectMocks
    TimelineService timelineService;

    @Mock
    TimelineRepository repository;

    @Mock
    MessageRepository messageRepository;

    @Mock
    TimelineProducer timelineProducer;

    @Test
    void testSaveMessage(){
        timelineService.saveMessage(new Message());
    }

    @Test
    void testSaveTimeline(){
        timelineService.saveTimelineData(new Timeline());
    }

    @Test
    void testPublishMessage(){
        timelineService.postMessage(new TimelineEvent());
    }
}
