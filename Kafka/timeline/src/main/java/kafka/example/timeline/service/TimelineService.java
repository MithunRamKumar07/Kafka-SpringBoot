package kafka.example.timeline.service;

import lombok.extern.slf4j.Slf4j;
import kafka.example.timeline.util.TimelineUtils;
import kafka.example.timeline.kafka.TimelineProducer;
import kafka.example.timeline.model.Message;
import kafka.example.timeline.model.Timeline;
import kafka.example.timeline.model.TimelineEvent;
import kafka.example.timeline.repository.MessageRepository;
import kafka.example.timeline.repository.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**<p> Service that has all the business logic for the Timeline service.
 * Coordinates the flow of events between the controller, repository and Kafka Modules</p>
 */
@Slf4j
@Service
public class TimelineService {
    @Autowired
    MessageRepository messageRepository;

    @Autowired
    TimelineRepository timelineRepository;

    @Autowired
    TimelineProducer timelineProducer;

    @Value("${timeline.topic}")
    String timelineTopic;

    public void saveMessage(Message message) {
        messageRepository.save(message);
        log.info("Message purged into the DB");
    }

    public void saveTimelineData(Timeline timeline) {
        timelineRepository.save(timeline);
        log.info("Timeline Data purged into the DB");
    }

    public void postMessage(TimelineEvent event) {
        timelineProducer.publishEventToAuthService(event,
                "checkMembershipStatus", TimelineUtils.TIMELINE_TOPIC);
    }
}
