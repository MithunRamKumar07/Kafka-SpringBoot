package nl.rabobank.mithun.assessment.customer.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthFailureListener {

    @KafkaListener(topics = "AUTH_FAILURE_TOPIC",groupId = "customer-group")
    public void listenToAuthFailureEvents(){

    }
}
