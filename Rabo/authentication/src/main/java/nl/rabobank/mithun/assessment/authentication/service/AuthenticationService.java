package nl.rabobank.mithun.assessment.authentication.service;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.mithun.assessment.authentication.kafka.AuthenticationPublisher;
import nl.rabobank.mithun.assessment.authentication.model.CustomerEvent;
import nl.rabobank.mithun.assessment.authentication.model.Membership;
import nl.rabobank.mithun.assessment.authentication.model.TimelineEvent;
import nl.rabobank.mithun.assessment.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository repository;

    @Autowired
    AuthenticationPublisher publisher;

    public void updateMembershipData(CustomerEvent customerEvent){
        log.info("Customer Object with Id {} is consumed from the Customer Service ", customerEvent.getCustomerId());
        if(customerEvent.getEventType().equals("createCustomer")){
            repository.save(createMembership(customerEvent));
        }else{
            Membership membership = repository.getMemberDetailsByCustomerId(customerEvent.getCustomerId());
            membership.setMembershipStatus(customerEvent.getMembershipStatus());
            repository.save(membership);
        }


    }

    public boolean isMembershipActive(TimelineEvent timelineEvent){
        Membership membership = repository.getMemberDetailsByCustomerId(timelineEvent.getCustomerId()); //Index the customerId column
        return String.valueOf(membership.getMembershipStatus()).equals("ACTIVE");
        }

    private static Membership createMembership(CustomerEvent customerEvent) {
        Membership membership = new Membership();
        membership.setCustomerId(customerEvent.getCustomerId());
        membership.setMembershipStatus(customerEvent.getMembershipStatus());
        membership.setCreatedAt(customerEvent.getCreatedAt());
        return membership;
    }
}
