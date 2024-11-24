package kafka.example.timeline.kafka;

import lombok.extern.slf4j.Slf4j;
import kafka.example.timeline.util.TimelineUtils;
import kafka.example.timeline.model.Message;
import kafka.example.timeline.model.Timeline;
import kafka.example.timeline.model.TimelineEvent;
import kafka.example.timeline.service.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;

/**<p> Kafka Consumer to consume the events from the Customer. Listens to the below topics</p>
 *  <li>CUSTOMER</li>
 */
@Slf4j
@Service
public class TimelineConsumer {

    @Autowired
    TimelineService timelineService;


    @RetryableTopic(attempts = "4") //n-1 times
    @KafkaListener(topics = TimelineUtils.AUTH_TOPIC,groupId = "timeline-group")
    public void listenAuthEvents(String event){
        log.info("Listening events from Authentication Service");
        TimelineEvent timelineEvent = TimelineUtils.getObjectFromString(event);
        if(timelineEvent.isMembershipActive()){
            timelineService.saveMessage(getMessage(timelineEvent));
            timelineService.saveTimelineData(getTimeline(timelineEvent));
            log.info("Data is successfully purged into the DB");
        } else{
            log.error("The Customer doesn't have a valid membership." +
                    "Cannot post message to the timeline");
        }
    }

    @DltHandler
    public void listenToDeadLetterQueue(){
        //Handle failure logic here
        log.info("Failed events are pushed to DL Topic");

    }

    private static Timeline getTimeline(TimelineEvent event) {
        Timeline timeline = new Timeline();
        timeline.setTimelineId(event.getTimelineId());
        timeline.setCustomerId(event.getCustomerId());
        return timeline;
    }

    private static Message getMessage(TimelineEvent timelineEvent) {
        Message message = new Message();
        message.setMessageContent(timelineEvent.getMessageContent());
        message.setTimelineId(timelineEvent.getTimelineId());
        return message;
    }
}