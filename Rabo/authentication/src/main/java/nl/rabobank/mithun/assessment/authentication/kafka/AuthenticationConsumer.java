package nl.rabobank.mithun.assessment.authentication.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.model.CustomerEvent;
import nl.rabobank.mithun.assessment.authentication.model.TimelineEvent;
import nl.rabobank.mithun.assessment.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.stereotype.Service;
import javax.naming.AuthenticationException;

/**<p> Kafka Consumer to consume the events from the Customer and Timeline Services. Listens to the below topics</p>
 *  <li>TIMELINE</li>
 *  <li>CUSTOMER</li>
 */
@Slf4j
@Service
public class AuthenticationConsumer {

    public static final String TIMELINE_TOPIC = "TIMELINE";
    public static final String AUTH_TOPIC = "AUTH";
    public static final String CUSTOMER_TOPIC = "CUSTOMER";
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthenticationProducer authenticationProducer;

    @RetryableTopic(attempts = "4")
    @KafkaListener(topics = CUSTOMER_TOPIC, groupId = "auth-group")
    public void listenCustomerEvents(String event) throws AuthenticationException {
        log.info("Listening customer creation/update from Customer Service");
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                CustomerEvent customerEvent = objectMapper.readValue(event, CustomerEvent.class);
                log.info("Updating membership Data in DB");
                authenticationService.updateMembershipData(customerEvent);
        } catch (JsonProcessingException e) {
            throw new AuthenticationException("Exception while parsing the JSON");
        }
    }

    @RetryableTopic(attempts = "4")
    @KafkaListener(topics = TIMELINE_TOPIC, groupId = "auth-timeline-group")
    public void listenTimelineEvents(String event) throws AuthenticationException {
        log.info("Listening message events from Customer Service");
        TimelineEvent timelineEvent = getObjectFromString(event);
        if(authenticationService.isMembershipActive(timelineEvent)){
            timelineEvent.setMembershipActive(true);
                // publish result as true
            authenticationProducer.publishEventToTimelineService(timelineEvent,"authenticationResult",
                    AUTH_TOPIC);
        }else{
            // publish result as false
            timelineEvent.setMembershipActive(false);
            authenticationProducer.publishEventToTimelineService(timelineEvent,"authenticationResult",
                    AUTH_TOPIC);
        }
    }

    @DltHandler
    public void listenToDeadLetterQueue(){
        //Handle failure logic here
        log.info("Failed events are pushed to DL Topic");
    }

    private TimelineEvent getObjectFromString(String event) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        TimelineEvent timelineEvent;
        try {
            timelineEvent = objectMapper.readValue(event,TimelineEvent.class);
        } catch (JsonProcessingException e) {
            throw new AuthenticationException("Exception while parsing the JSON");
        }
        return timelineEvent;
    }
}
