package nl.rabobank.mithun.assessment.customer.service;


import nl.rabobank.mithun.assessment.customer.exception.CustomerException;
import nl.rabobank.mithun.assessment.customer.kafka.CustomerMessageProducer;
import nl.rabobank.mithun.assessment.customer.model.Customer;
import nl.rabobank.mithun.assessment.customer.model.Status;
import nl.rabobank.mithun.assessment.customer.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/** <p> Test class for {@link CustomerService }</p>
 *
 */
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Mock
    List<Customer> customers;

    @Mock
    CustomerMessageProducer customerMessageProducer;

    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(customerService,"customerTopic","CUSTOMER");
    }

    @Test
    public void testCreateCustomer(){
        when(customerRepository.save(any())).thenReturn(customer);
        assertNotNull(customerService.createCustomer(customer));
    }

    @Test
    public void testUpdateCustomer (){
        when(customerRepository.getReferenceById(anyInt())).thenReturn(customer);
        when(customerRepository.save(any())).thenReturn(customer);
        assertNotNull(customerService.updateCustomer(customer));
    }


    @Test
    public void testUpdateCustomerForException(){
        assertThrows(CustomerException.class,()->customerService.updateCustomer(customer));
    }

    @Test
    public void testGetCustomerById(){
        when(customerRepository.getReferenceById(any())).thenReturn(customer);
        assertNotNull(customerService.getCustomer(1));
    }

    @Test
    public void testGetAllCustomers(){
        when(customerRepository.findAll()).thenReturn(customers);
        assertNotNull(customerService.getAllCustomers());
    }

    @Test
    public void testDeleteCustomer(){
       customerService.deleteCustomer(1);
    }

}
