package kafka.example.timeline.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import kafka.example.timeline.exception.TimelineException;
import kafka.example.timeline.model.TimelineEvent;
/**<p> Util class to handle the common logic across the timeline module.
 */
@Slf4j
public class TimelineUtils {

    public static final String TIMELINE_TOPIC = "TIMELINE";
    public static final String AUTH_TOPIC = "AUTH";

    public static String getStringFromObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventAsString;
        try {
            eventAsString = objectMapper.writeValueAsString(object);
            log.info("Message to be published : {}" , eventAsString);
        } catch (JsonProcessingException e) {
            throw new TimelineException("Exception while parsing the JSON");
        }
        return eventAsString;
    }

    public static TimelineEvent getObjectFromString(String event){
        ObjectMapper objectMapper = new ObjectMapper();
        TimelineEvent timelineEvent;
        try {
            timelineEvent = objectMapper.readValue(event,TimelineEvent.class);
        } catch (JsonProcessingException e) {
            throw new TimelineException("Exception while parsing the JSON");
        }
        return timelineEvent;
    }
}
