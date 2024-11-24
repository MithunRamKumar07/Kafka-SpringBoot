package kafka.example.timeline.controller;

import kafka.example.timeline.model.TimelineEvent;
import kafka.example.timeline.service.TimelineService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/** <p> Test class for {@link TimelineController }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class TimelineControllerTest {

    @InjectMocks
    TimelineController timelineController;

    @Mock
    TimelineService timelineService;

    @Test
    void testPostMessage(){
        timelineController.addMessages(new TimelineEvent());
    }
}
