package nl.rabobank.mithun.assessment.customer.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerListener {

    @KafkaListener(topics = "AUTH_FAILURE_TOPIC",groupId = "customer-group")
    public void listenToAuthFailureEvents(String errorEvent){
        log.error("Authentication Failure for user. Error Details : {}" , errorEvent );
    }
}
