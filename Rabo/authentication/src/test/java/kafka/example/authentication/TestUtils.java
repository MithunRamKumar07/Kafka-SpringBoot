package kafka.example.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kafka.example.authentication.exception.AuthenticationException;
import kafka.example.authentication.model.CustomerEvent;
import kafka.example.authentication.model.Membership;
import kafka.example.authentication.model.Status;
import kafka.example.authentication.model.TimelineEvent;

public class TestUtils {

    public static CustomerEvent getCustomerEvent(String eventType) {
        CustomerEvent customerEvent = new CustomerEvent();
        customerEvent.setEventType(eventType);
        return customerEvent;
    }

    public static TimelineEvent getTimelineEvent() {
        TimelineEvent timelineEvent = new TimelineEvent();
        timelineEvent.setCustomerId(1);
        return timelineEvent;
    }

    public static Membership getMembership(Status status){
        Membership membership = new Membership();
        membership.setMembershipStatus(status);
        return membership;
    }

    public static String getStringFromObject(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        String eventAsString;
        try {
            eventAsString = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new AuthenticationException("Exception while parsing the JSON");
        }
        return eventAsString;
    }



}
