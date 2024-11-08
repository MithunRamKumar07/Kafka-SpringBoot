package nl.rabobank.mithun.assessment.authentication.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.model.Customer;
import nl.rabobank.mithun.assessment.authentication.model.Message;
import nl.rabobank.mithun.assessment.authentication.model.Timeline;
import nl.rabobank.mithun.assessment.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationListener {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    AuthorizationPublisher authorizationPublisher;
    ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "CUSTOMER", groupId = "auth-group")
    public void listenCustomerEvents(String event) {
        log.info("Listening customer creation/update from Customer Service");

        try {
            Customer customer = objectMapper.readValue(event, Customer.class);
            log.info("Updating membership Data");
            authenticationService.updateMembershipData(customer);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @KafkaListener(topics = "MESSAGES", groupId = "auth-group")
    public void listenMessageEvents(String event) {
        log.info("Listening message events from Customer Service");
        try {
            Message message = objectMapper.readValue(event, Message.class);
            Timeline timeline = message.getTimeline();
            if (authenticationService.isMembershipActive(timeline)) {
                log.info("Publishing message events to Timeline Service");
                authorizationPublisher.publishEvent(message, "postMessage", "AUTH_TOPIC");
            } else {
                log.info("Authentication Failure. Publishing Auth failure events to Customer Service");
                authorizationPublisher.publishEvent("The customer with Id" + timeline .getCustomerId() + "doesn't have an active membership",
                        "authenticationFailure", "AUTH_FAILURE_TOPIC");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
