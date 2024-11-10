package nl.rabobank.mithun.assessment.timeline.service;

import nl.rabobank.mithun.assessment.timeline.exception.TimelineException;
import nl.rabobank.mithun.assessment.timeline.exception.TimelineExceptionHandler;
import nl.rabobank.mithun.assessment.timeline.kafka.TimelineProducer;
import nl.rabobank.mithun.assessment.timeline.model.Message;
import nl.rabobank.mithun.assessment.timeline.model.Timeline;
import nl.rabobank.mithun.assessment.timeline.model.TimelineEvent;
import nl.rabobank.mithun.assessment.timeline.repository.MessageRepository;
import nl.rabobank.mithun.assessment.timeline.repository.TimelineRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Time;

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
