package kafka.example.authentication.service;

import lombok.extern.slf4j.Slf4j;
import kafka.example.authentication.kafka.AuthenticationProducer;
import kafka.example.authentication.model.CustomerEvent;
import kafka.example.authentication.model.Membership;
import kafka.example.authentication.model.TimelineEvent;
import kafka.example.authentication.repository.AuthenticationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kafka.example.authentication.exception.AuthenticationException;

/**<p> Service that has all the business logic for the Auth service. Coordinates the flow of events between the controller, repository and Kafka Modules</p>
 */
@Slf4j
@Service
public class AuthenticationService {

    @Autowired
    AuthenticationRepository repository;

    @Autowired
    AuthenticationProducer publisher;

    public void updateMembershipData(CustomerEvent customerEvent) throws AuthenticationException {
        log.info("Updating Data for Customer with Id {} ", customerEvent.getCustomerId());
        switch(customerEvent.getEventType()){
            // Events for Crud Operations
            case "createCustomer" -> {
                repository.save(createMembership(customerEvent));
                log.info("New membership created for customer with Id : {} ",customerEvent.getCustomerId());
            }
            case "updateCustomer" -> {
                Membership membership = getMemberDetailsByCustomerId(customerEvent);
                if(membership!=null){
                    membership.setMembershipStatus(customerEvent.getMembershipStatus());
                    repository.save(membership);
                }else {
                    log.error("Membership is not found to update");
                    throw new AuthenticationException("Membership not found");
                }
            }
            case "deleteCustomer" -> {
                Membership membership = getMemberDetailsByCustomerId(customerEvent);
                if(membership != null){
                    repository.delete(membership);
                    log.info("Membership deleted for customer with Id : {} ",customerEvent.getCustomerId());
                }
                else {
                    log.error("Membership is not found to delete");
                    throw new AuthenticationException("Membership not found");
                }
            }
            default -> log.info("Do Nothing.All scenarios are handled");
        }
    }

    private Membership getMemberDetailsByCustomerId(CustomerEvent customerEvent) {
        return repository.getMemberDetailsByCustomerId(customerEvent.getCustomerId());
    }

    public boolean isMembershipActive(TimelineEvent timelineEvent){
        Membership membership = repository.getMemberDetailsByCustomerId(timelineEvent.getCustomerId());
        return membership != null && String.valueOf(membership.getMembershipStatus()).equals("ACTIVE");
    }

    private static Membership createMembership(CustomerEvent customerEvent) {
        Membership membership = new Membership();
        membership.setCustomerId(customerEvent.getCustomerId());
        membership.setMembershipStatus(customerEvent.getMembershipStatus());
        membership.setCreatedAt(customerEvent.getCreatedAt());
        return membership;
    }
}
