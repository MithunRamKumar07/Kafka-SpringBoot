package nl.rabobank.mithun.assessment.authentication.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.model.Customer;
import nl.rabobank.mithun.assessment.authentication.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationListener {

    @Autowired
    AuthenticationService authenticationService;

    @KafkaListener(topics = "CUSTOMER_TOPIC",groupId = "auth-group")
    public void listenCustomerEvents(String event){
        log.info("Listening events from Customer Service");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // This check is an issue
            Customer customer = objectMapper.readValue(event,Customer.class);
            if(customer.getOperationType().equals("eligibilityCheck")){
                log.info("Performing Eligibility Check");
                authenticationService.checkMembershipEligibility(customer);
//                if(eventType.equalsIgnoreCase("postMessage")){
                if(true){
                    authenticationService.postMessageToTimeline(customer);
                }else{
                    authenticationService.publishAuthenticationFailureForCustomers(customer);
                }
            }else{
                log.info("Updating membership Data");
                authenticationService.updateMembershipData(customer);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
