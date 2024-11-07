package nl.rabobank.mithun.assessment.customer.service;

import nl.rabobank.mithun.assessment.customer.kafka.CustomerMessagePublisher;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.model.Message;
import nl.rabobank.mithun.assessment.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMessagePublisher customerPublisher;


    public void createCustomer(Customer customer){
        customer.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        customer.setEventType("createCustomer");
        customerRepository.save(customer);
        //publish an event to the Authentication Service
        customerPublisher.publishCustomerDataToAuthService(customer);
    }

    public Customer getCustomer(int customerId){
        return customerRepository.getById(customerId);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void updateCustomer( Customer customer){
        customer.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        customer.setEventType("updateMembershipStatus");
        customerRepository.save(customer);
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteById(customerId);
    }

    public void publishCustomerDataToAuthService(Customer customer) {
        //Event 1- Send the message to Authentication Service to check eligibility
        customerPublisher.publishCustomerDataToAuthService(customer);
        // Event 2 - Send event to Timeline service to add the message.
        //Also write logic to receive the status from the Authentication service
    }

    public void postMessage(Message message){
        customerPublisher.postMessage(message);
    }
}
