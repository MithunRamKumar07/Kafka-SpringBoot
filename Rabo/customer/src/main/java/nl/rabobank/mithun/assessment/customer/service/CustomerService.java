package nl.rabobank.mithun.assessment.customer.service;

import nl.rabobank.mithun.assessment.customer.exception.CustomerException;
import nl.rabobank.mithun.assessment.customer.kafka.CustomerMessageProducer;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**<p> Service that has all the business logic for the Customer service.
 * Coordinates the flow of events between the controller, repository and Kafka Modules</p>
 */
@Service
public class CustomerService {

    public static final String CREATE_CUSTOMER = "createCustomer";
    public static final String UPDATE_CUSTOMER = "updateCustomer";
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMessageProducer customerPublisher;

    @Value("${customer.topic}")
    String customerTopic;


    public Customer createCustomer(Customer customer){
        customer.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        customer.setEventType(CREATE_CUSTOMER);
        Customer response = customerRepository.save(customer);
        //publish an event to the Authentication Service
        customerPublisher.publishCustomerDataToAuthService(customer, CREATE_CUSTOMER, customerTopic);
        return response;
    }

    public Customer getCustomer(int customerId){
        return customerRepository.getReferenceById(customerId);
    }
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer){
        Customer entityToBeUpdated = customerRepository.getReferenceById(customer.getCustomerId());
        if(entityToBeUpdated == null){
            throw new CustomerException("The customer to be updated is not present. Please provide a valid customer Id");
        }
        entityToBeUpdated.setMembershipStatus(customer.getMembershipStatus());
        entityToBeUpdated.setCreatedAt(Timestamp.valueOf(java.time.LocalDateTime.now()));
        entityToBeUpdated.setEventType(UPDATE_CUSTOMER);
        Customer response = customerRepository.save(entityToBeUpdated);
        customerPublisher.publishCustomerDataToAuthService(entityToBeUpdated, UPDATE_CUSTOMER, customerTopic);
        return response;
    }

    public void deleteCustomer(int customerId){
        customerRepository.deleteById(customerId);
        // The membership related to the customer should be deleted
        customerPublisher.publishCustomerDataToAuthService(getDeleteCustomerData(customerId), UPDATE_CUSTOMER, customerTopic);
    }

    private Customer getDeleteCustomerData(int customerId) {
        Customer customerToBeDeleted = new Customer();
        customerToBeDeleted.setCustomerId(customerId);
        customerToBeDeleted.setEventType("deleteCustomer");
        return customerToBeDeleted;
    }
}
