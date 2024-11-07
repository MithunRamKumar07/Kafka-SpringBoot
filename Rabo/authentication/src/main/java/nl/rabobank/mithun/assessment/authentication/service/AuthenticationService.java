package nl.rabobank.mithun.assessment.authentication.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.kafka.AuthorizationPublisher;
import nl.rabobank.mithun.assessment.authentication.model.Customer;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.repository.AuthenticationRepository;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository repository;

    @Autowired
    AuthorizationPublisher authorizationPublisher;

    public void updateMembershipData(Customer customer){
        Membership membership = getMembership(customer);
        repository.save(membership);
        log.info("Customer Object with Id {} is consumed from the Customer Service ",customer.getCustomerId());
    }

    public String checkMembershipEligibility(Customer customer){
        Membership membership = repository.getMemberDetailsByCustomerId(customer.getCustomerId());
        // Need to decide whether we need to call the Timeline Service or the Customer Service
        var result = String.valueOf(membership.getMembershipStatus());
        return  result;
    }

    private static Membership getMembership(Customer customer) {
        Membership membership = new Membership();
        membership.setCustomerId(customer.getCustomerId());
        membership.setMembershipStatus(customer.getMembershipStatus());
        membership.setCreatedAt(customer.getCreatedAt());
        return membership;
    }

    public void postMessageToTimeline(Message message) {
        authorizationPublisher.publishEvent(message,"postMessageToTimeline");
    }

    public void publishAuthenticationFailureForCustomers(Customer customer) {
        authorizationPublisher.publishEvent(customer,"unAuthorisedUser");
    }
}
