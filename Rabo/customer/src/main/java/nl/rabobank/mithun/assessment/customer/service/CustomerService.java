package nl.rabobank.mithun.assessment.customer.service;

import nl.rabobank.mithun.assessment.customer.kafka.CustomerMessagePublisher;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.repository.CustomerRepository;
import nl.rabobank.mithun.assessment.customer.util.CustomerConstants;
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
        customerPublisher.publishCustomerDataToAuthService(customer,"createCustomer", CustomerConstants.CUSTOMER_TOPIC);
    }

    public Customer getCustomer(int customerId){
        return customerRepository.getReferenceById(customerId);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public void updateCustomer(Customer customer){
        Customer entityToBeUpdated = customerRepository.getReferenceById(customer.getCustomerId());
        entityToBeUpdated.setMembershipStatus(customer.getMembershipStatus());
        entityToBeUpdated.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        entityToBeUpdated.setEventType("updateMembershipStatus");
        customerRepository.save(entityToBeUpdated);
        customerPublisher.publishCustomerDataToAuthService(entityToBeUpdated,"updateCustomer", CustomerConstants.CUSTOMER_TOPIC);
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteById(customerId);
    }

}
