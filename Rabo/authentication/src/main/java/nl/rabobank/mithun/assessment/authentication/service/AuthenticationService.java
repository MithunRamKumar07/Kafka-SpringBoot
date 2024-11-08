package nl.rabobank.mithun.assessment.authentication.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.kafka.AuthorizationPublisher;
import nl.rabobank.mithun.assessment.authentication.model.Customer;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.model.Timeline;
import nl.rabobank.mithun.assessment.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository repository;

    public void updateMembershipData(Customer customer){
        log.info("Customer Object with Id {} is consumed from the Customer Service ",customer.getCustomerId());
        Membership membership = getMembership(customer);
        repository.save(membership);

    }

    public boolean isMembershipActive(Timeline timeline){
        Membership membership = repository.getMemberDetailsByCustomerId(timeline.getCustomerId()); //Index the customerId column
        return String.valueOf(membership.getMembershipStatus()).equals("ACTIVE");
    }

    private static Membership getMembership(Customer customer) {
        Membership membership = new Membership();
        membership.setCustomerId(customer.getCustomerId());
        membership.setMembershipStatus(customer.getMembershipStatus());
        membership.setCreatedAt(customer.getCreatedAt());
        return membership;
    }
}
