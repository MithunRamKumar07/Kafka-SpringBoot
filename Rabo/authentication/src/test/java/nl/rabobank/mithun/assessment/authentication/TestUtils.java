package nl.rabobank.mithun.assessment.authentication;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.rabobank.mithun.assessment.authentication.exception.AuthenticationException;
import nl.rabobank.mithun.assessment.authentication.model.CustomerEvent;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.model.Status;
import nl.rabobank.mithun.assessment.authentication.model.TimelineEvent;

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
